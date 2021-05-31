[一个站点配置多个域名:]--------------------------------------------------------------------------------
server {
    listen 80;
    server_name aaa.cn bbb.cn;
}
server_name 后跟多个域名即可，多个域名之间用空格分隔

[一个服务配置多个站点]--------------------------------------------------------------------------------
server {
    listen 80;
    server_name aaa.cn;

    location / {
        root /home/project/pa;
        index index.html;
    }
}

server {
    listen 80;
    server_name bbb.cn ccc.cn;

    location / {
        root /home/project/pb;
        index index.html;
    }
}

server {
    listen 80;
    server_name ddd.cn;

    location / {
        root /home/project/pc;
        index index.html;
    }
}
基于Nginx虚拟主机配置实现，Nginx有三种类型的虚拟主机
1.基于IP的虚拟主机： 需要你的服务器上有多个地址，每个站点对应不同的地址，这种方式使用的比较少
2.基于端口的虚拟主机： 每个站点对应不同的端口，访问的时候使用ip:port的方式访问，可以修改listen的端口来使用
3.基于域名的虚拟主机： 使用最广的方式，上边例子中就是用了基于域名的虚拟主机，前提条件是你有多个域名分别对应每个站点，server_name填写不同的域名即可

[静态资源缓存]---------------------------------------------------------------------------------
请根据您的实际情况进行筛选

location ~ .*\.(?:js|css|jpg|jpeg|gif|png|ico|cur|gz|svg|svgz|mp4|ogg|ogv|webm)$ {
    expires 7d;
}

location ~ .*\.(?:htm|html)$ {
    add_header Cache-Control "private, no-store, no-cache, must-revalidate, proxy-revalidate";
}

这里注意：no-cache与no-store的区别，no-cache表示不缓存过期资源，缓存会向服务器进行有效处理确认之后处理资源，而no-store才是真正的不进行缓存。

[开启gzip压缩]---------------------------------------------------------------------------------
http {
    gzip on; #开启gzip压缩功能
    gzip_disable "MSIE [1-6]\.(?!.*SV1)"; #配置禁用gzip条件，支持正则。此处表示ie6及以下不启用gzip（因为ie低版本不支持）
    gzip_proxied any; #无条件压缩所有结果数据
    gzip_min_length 10k; #设置允许压缩的页面最小字节数; 这里表示如果文件小于10个字节，就不用压缩，因为没有意义，本来就很小.
    gzip_comp_level 6; #设置压缩比率，最小为1，处理速度快，传输速度慢；9为最大压缩比，处理速度慢，传输速度快; 这里表示压缩级别，可以是0到9中的任一个，级别越高，压缩就越小，节省了带宽资源，但同时也消耗CPU资源，所以一般折中为6
    gzip_buffers 16 8k; #设置压缩缓冲区大小，此处设置为16个8K内存作为压缩结果流缓存
    gzip_http_version 1.1; #压缩版本
    gzip_types text/plain text/css application/json application/x-javascript text/xml application/xml application/xml+rss text/javascript; #制定压缩的类型,线上配置时尽可能配置多的压缩类型!
    gzip_vary on; #选择支持vary header；改选项可以让前端的缓存服务器缓存经过gzip压缩的页面; 这个可以不写，表示在传送数据时，给客户端说明我使用了gzip压缩
}

[CPU亲和力优化]---------------------------------------------------------------------------------
默认情况下可能多个进程跑在一个CPU上或某一核上，导致Nginx进程使用硬件资源不均匀，此次优化是尽可能地分配不同的Nginx进程给不同的CPU处理

两颗CPU参数配置：
    worker_processes 2;
    worker_cpu_affinity 0101 1010;

四颗CPU参数配置：
    worker_processes 4;
    worker_cpu_affinity 0001 0010 0100 1000;

八颗CPU参数配置：
    worker_processes 8;
    worker_cpu_affinity 00000001 00000010 00000100 00001000 00010000 00100000 01000000 10000000;

---[Nginx运行工作进程数量]---------------------------------------------------------------------------------
Nginx运行工作进程个数一般设置CPU的核心或者核心数x2。如果不了解cpu的核数，可以top命令之后按1看出来，也可以查看/proc/cpuinfo文件 grep ^processor /proc/cpuinfo | wc -l

[root@lx~]# vi/usr/local/nginx1.10/conf/nginx.conf
worker_processes 4;
[root@lx~]# /usr/local/nginx1.10/sbin/nginx -s reload
[root@lx~]# ps -aux | grep nginx |grep -v grep

root 9834 0.0 0.0 47556 1948 ? Ss 22:36 0:00 nginx: master processnginx

www 10135 0.0 0.0 50088 2004 ? S 22:58 0:00 nginx: worker process
www 10136 0.0 0.0 50088 2004 ? S 22:58 0:00 nginx: worker process
www 10137 0.0 0.0 50088 2004 ? S 22:58 0:00 nginx: worker process
www 10138 0.0 0.0 50088 2004 ? S 22:58 0:00 nginx: worker process

[Nginx最大打开文件数]---------------------------------------------------------------------------------
worker_rlimit_nofile 65535;

这个指令是指当一个nginx进程打开的最多文件描述符数目，理论值应该是最多打开文件数（ulimit -n）与nginx进程数相除，但是nginx分配请求并不是那么均匀，所以最好与ulimit -n的值保持一致。
注：文件资源限制的配置可以在/etc/security/limits.conf设置，针对root/user等各个用户或者*代表所有用户来设置。
linux 默认值 open files为1024。查看当前系统值：

使用ulimit -a 可以查看当前系统的所有限制值，使用ulimit -n 可以查看当前的最大打开文件数。

新装的linux 默认只有1024 ，当作负载较大的服务器时，很容易遇到error: too many open files。因此，需要将其改大，在/etc/security/limits.conf最后增加：
* soft nofile 65535
* hard nofile 65535
* soft noproc 65535
* hard noproc 65535

用户重新登录生效（ulimit -n）

[Nginx事件处理模型]---------------------------------------------------------------------------------
events {
    use epoll;
    worker_connections 65535;
    multi_accept on;
}

nginx采用epoll事件模型，处理效率高。

work_connections是单个worker进程允许客户端最大连接数，这个数值一般根据服务器性能和内存来制定，实际最大值就是worker进程数乘以work_connections。
实际我们填入一个65535，足够了，这些都算并发值，一个网站的并发达到这么大的数量，也算一个大站了！
multi_accept 告诉nginx收到一个新连接通知后接受尽可能多的连接，默认是on，
设置为on后，多个worker按串行方式来处理连接，也就是一个连接只有一个worker被唤醒，其他的处于休眠状态，
设置为off后，多个worker按并行方式来处理连接，也就是一个连接会唤醒所有的worker，直到连接分配完毕，没有取得连接的继续休眠。
当你的服务器连接数不多时，开启这个参数会让负载有一定的降低，但是当服务器的吞吐量很大时，为了效率，可以关闭这个参数。

[开启高效传输模式]---------------------------------------------------------------------------------
http {
    include mime.types;
    default_type application/octet-stream;
    ……
    sendfile on;
    tcp_nopush on;
    ……
}

Include mime.types ：媒体类型,include 只是一个在当前文件中包含另一个文件内容的指令。
default_type application/octet-stream ：默认媒体类型足够。
sendfile on：开启高效文件传输模式，sendfile指令指定nginx是否调用sendfile函数来输出文件，对于普通应用设为 on，
    如果用来进行下载等应用磁盘IO重负载应用，可设置为off，以平衡磁盘与网络I/O处理速度，降低系统的负载。注意：如果图片显示不正常把这个改成off。
tcp_nopush on：必须在sendfile开启模式才有效，防止网路阻塞，积极的减少网络报文段的数量（将响应头和正文的开始部分一起发送，而不一个接一个的发送。）

[连接超时时间]---------------------------------------------------------------------------------
主要目的是保护服务器资源，CPU，内存，控制连接数，因为建立连接也是需要消耗资源的。

keepalive_timeout 60;
tcp_nodelay on;
client_header_buffer_size 4k;
open_file_cache max=102400 inactive=20s;
open_file_cache_valid 30s;
open_file_cache_min_uses 1;
client_header_timeout 15;
client_body_timeout 15;
reset_timeout_connection on;
send_timeout 15;
server_tokens off;
client_max_body_size 10m;

keepalived_timeout ：客户端连接保持会话超时时间，超过这个时间，服务器断开这个链接。
tcp_nodelay：也是防止网络阻塞，不过要包涵在keepalived参数才有效。
client_header_buffer_size 4k：客户端请求头部的缓冲区大小，这个可以根据你的系统分页大小来设置，一般一个请求头的大小不会超过 1k，不过由于一般系统分页都要大于1k，所以这里设置为分页大小。分页大小可以用命令getconf PAGESIZE取得。
open_file_cache max=102400 inactive=20s ：这个将为打开文件指定缓存，默认是没有启用的，max指定缓存数量，建议和打开文件数一致，inactive 是指经过多长时间文件没被请求后删除缓存。
open_file_cache_valid 30s：这个是指多长时间检查一次缓存的有效信息。
open_file_cache_min_uses 1 ：open_file_cache指令中的inactive 参数时间内文件的最少使用次数，如果超过这个数字，文件描述符一直是在缓存中打开的，如上例，如果有一个文件在inactive 时间内一次没被使用，它将被移除。
client_header_timeout ：设置请求头的超时时间。我们也可以把这个设置低些，如果超过这个时间没有发送任何数据，nginx将返回request time out的错误。
client_body_timeout设置请求体的超时时间。我们也可以把这个设置低些，超过这个时间没有发送任何数据，和上面一样的错误提示。
reset_timeout_connection ：告诉nginx关闭不响应的客户端连接。这将会释放那个客户端所占有的内存空间。
send_timeout ：响应客户端超时时间，这个超时时间仅限于两个活动之间的时间，如果超过这个时间，客户端没有任何活动，nginx关闭连接。
server_tokens ：并不会让nginx执行的速度更快，但它可以关闭在错误页面中的nginx版本数字，这样对于安全性是有好处的。
client_max_body_size：上传文件大小限制


[fastcgi 调优]---------------------------------------------------------------------------------
fastcgi_connect_timeout 600;
fastcgi_send_timeout 600;
fastcgi_read_timeout 600;
fastcgi_buffer_size 64k;
fastcgi_buffers 4 64k;
fastcgi_busy_buffers_size 128k;
fastcgi_temp_file_write_size 128k;
fastcgi_temp_path/usr/local/nginx1.10/nginx_tmp;
fastcgi_intercept_errors on;
fastcgi_cache_path/usr/local/nginx1.10/fastcgi_cache levels=1:2
keys_zone=cache_fastcgi:128minactive=1d max_size=10g;

fastcgi_connect_timeout 600 ：指定连接到后端FastCGI的超时时间。
fastcgi_send_timeout 600 ：向FastCGI传送请求的超时时间。
fastcgi_read_timeout 600 ：指定接收FastCGI应答的超时时间。
fastcgi_buffer_size 64k ：指定读取FastCGI应答第一部分需要用多大的缓冲区，默认的缓冲区大小为。fastcgi_buffers指令中的每块大小，可以将这个值设置更小。
fastcgi_buffers 4 64k ：指定本地需要用多少和多大的缓冲区来缓冲FastCGI的应答请求，如果一个php脚本所产生的页面大小为256KB，那么会分配4个64KB的缓冲区来缓存，如果页面大小大于256KB，那么大于256KB的部分会缓存到fastcgi_temp_path指定的路径中，但是这并不是好方法，因为内存中的数据处理速度要快于磁盘。一般这个值应该为站点中php脚本所产生的页面大小的中间值，如果站点大部分脚本所产生的页面大小为256KB，那么可以把这个值设置为“8 32K”、“4 64k”等。
fastcgi_busy_buffers_size 128k ：建议设置为fastcgi_buffers的两倍，繁忙时候的buffer。
fastcgi_temp_file_write_size 128k ：在写入fastcgi_temp_path时将用多大的数据块，默认值是fastcgi_buffers的两倍，该数值设置小时若负载上来时可能报502BadGateway。
fastcgi_temp_path ：缓存临时目录。
fastcgi_intercept_errors on ：这个指令指定是否传递4xx和5xx错误信息到客户端，或者允许nginx使用error_page处理错误信息。注：静态文件不存在会返回404页面，但是php页面则返回空白页！
fastcgi_cache_path /usr/local/nginx1.10/fastcgi_cachelevels=1:2 keys_zone=cache_fastcgi:128minactive=1d max_size=10g ：fastcgi_cache缓存目录，可以设置目录层级，比如1:2会生成16*256个子目录，cache_fastcgi是这个缓存空间的名字，cache是用多少内存（这样热门的内容nginx直接放内存，提高访问速度），inactive表示默认失效时间，如果缓存数据在失效时间内没有被访问,将被删除，max_size表示最多用多少硬盘空间。
fastcgi_cache cache_fastcgi ：#表示开启FastCGI缓存并为其指定一个名称。开启缓存非常有用，可以有效降低CPU的负载，并且防止502的错误放生。cache_fastcgi为proxy_cache_path指令创建的缓存区名称。
fastcgi_cache_valid 200 302 1h ：#用来指定应答代码的缓存时间，实例中的值表示将200和302应答缓存一小时，要和fastcgi_cache配合使用。
fastcgi_cache_valid 301 1d ：将301应答缓存一天。
fastcgi_cache_valid any 1m ：将其他应答缓存为1分钟。
fastcgi_cache_min_uses 1 ：该指令用于设置经过多少次请求的相同URL将被缓存。
fastcgi_cache_key http://$host$request_uri ：该指令用来设置web缓存的Key值,nginx根据Key值md5哈希存储.一般根据$host(域名)、$request_uri(请求的路径)等变量组合成proxy_cache_key 。
fastcgi_pass ：指定FastCGI服务器监听端口与地址，可以是本机或者其它。

[开启pathinfo模式]---------------------------------------------------------------------------------
我们用thinkphp，CodeIgniter框架的时候，地址基本都是/index.php/group_controller？***的模式，
通过index.php入口访问php文件，这种模式是path_info模式，pathinfo 模式是index.ph/index/index 这种url格式，nginx默认是不支持的，我们需要配置下

location ~ \.php {
    include fastcgi_params;
    fastcgi_pass php-fpm:9000;
    fastcgi_index index.php;
    fastcgi_param SCRIPT_FILENAME /data/www/$fastcgi_script_name;
    # 添加以下三行 支持
    pathinfofastcgi_split_path_info ^(.+\.php)(.*)$;
    fastcgi_param PATH_INFO $fastcgi_path_info;
    include fastcgi_params;
}

[配置默认站点]---------------------------------------------------------------------------------
server {
    listen 80 default;
}

当一个nginx服务上创建了多个虚拟主机时默认会从上到下查找，如果匹配不到虚拟主机则会返回第一个虚拟主机的内容，
如果你想指定一个默认站点时，可以将这个站点的虚拟主机放在配置文件中第一个虚拟主机的位置，或者在这个站点的虚拟主机上配置listen default

---[nginx添加账号密码验证]---------------------------------------------------------------------------------
server {
    location / {
        auth_basic "please input user&passwd";
        auth_basic_user_file key/auth.key;
    }
}

有很多服务通过nginx访问，但本身没有提供账号认证的功能，就可以通过nginx提供的authbase账号密码认证来实现，可以用以下脚本来生成账号的密码
# cat pwd.pl

#!/usr/bin/perluse strict;
my $pw=$ARGV[0] ;
print crypt($pw,$pw)."\n";

使用方法：
# perl pwd.pl opf8BImqCAXww
# echo "admin:opf8BImqCAXww" > key/auth.key


[nginx开启列目录-文件下载]---------------------------------------------------------------------------------
当你想让nginx作为文件下载服务器存在时，需要开启nginx列目录
server {
    location download {
        autoindex on;
        autoindex_exact_size off;
        autoindex_localtime on;
    }
}

autoindex_exact_size： 为on(默认)时显示文件的确切大小，单位是byte；改为off显示文件大概大小，单位KB或MB或GB
autoindex_localtime： 为off(默认)时显示的文件时间为GMT时间；改为on后，显示的文件时间为服务器时间

默认当访问列出的txt等文件时会在浏览器上显示文件的内容，如果你想让浏览器直接下载，加上下边的配置

if ($request_filename ~* ^.*?\.(txt|pdf|jpg|png)$) {
    add_header Content-Disposition 'attachment';
}


[不允许通过IP访问]---------------------------------------------------------------------------------
server {
    listen 80 default;
    server_name _;
    return 404;
}

可能有一些未备案的域名或者你不希望的域名将服务器地址指向了你的服务器，这时候就会对你的站点造成一定的影响，需要禁止IP或未配置的域名访问，
我们利用上边所说的default规则，将默认流量都转到404去

上边这个方法比较粗暴，当然你也可以配置下所有未配置的地址访问时直接301重定向到你的网站去，也能为你的网站带来一定的流量

server {
    rewrite ^/(.*)$ https://baidu.com/$1 permanent;
}

---[直接返回验证文件]---------------------------------------------------------------------------------
location = /XDFyle6tNA.txt {
    default_type text/plain;
    return 200 'd6296a84657eb275c05c31b10924f6ea';
}
很多时候微信等程序都需要我们放一个txt的文件到项目里以验证项目归属，我们可以直接通过上边这种方式修改nginx即可，无需真正的把文件给放到服务器上

---[nginx配置upstream反向代理]---------------------------------------------------------------------------------
http {
    ...
    upstream tomcats {
        server 192.168.106.176 weight=1;
        server 192.168.106.177 weight=1;
    }

    server {
        location /ops-coffee/ {
            proxy_pass http://tomcats; proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    }
}

稍不注意可能会落入一个proxy_pass加杠不加杠的陷阱，这里详细说下
proxy_pass http://tomcats   
与   
proxy_pass http://tomcats/  
的区别：

虽然只是一个/的区别但结果确千差万别。分为以下两种情况：

1. 目标地址中不带uri（proxy_pass http://tomcats）。此时新的目标url中，匹配的uri部分不做修改，原来是什么就是什么。

location /ops-coffee/ {
    proxy_pass http://192.168.106.135:8181;
}

http://domain/ops-coffee/ --> http://192.168.106.135:8181/ops-coffee/
http://domain/ops-coffee/action/abc --> http://192.168.106.135:8181/ops-coffee/action/abc

2. 目标地址中带uri（proxy_pass http://tomcats/，/也是uri）,此时新的目标url中，匹配的uri部分将会被修改为该参数中的uri。

location /ops-coffee/ {
    proxy_pass http://192.168.106.135:8181/;
}

http://domain/ops-coffee/ --> http://192.168.106.135:8181
http://domain/ops-coffee/action/abc --> http://192.168.106.135:8181/action/abc


[nginx upstream开启keepalive]---------------------------------------------------------------------------------
upstream tomcat {
    server www.baidu.com:8080;
    keepalive 1024;
}

server {
    location / {
        proxy_http_version 1.1;
        proxy_set_header Connection "";
        proxy_pass http://tomcat;
    }
}

nginx在项目中大多数情况下会作为反向代理使用，例如nginx后接tomcat，nginx后接php等，这时我们开启nginx和后端服务之间的keepalive能够减少频繁创建TCP连接造成的资源消耗，配置如上

keepalive： 指定每个nginxworker可以保持的最大连接数量为1024，默认不设置，即nginx作为client时keepalive未生效
proxy_http_version 1.1： 开启keepalive要求HTTP协议版本为HTTP 1.1
proxy_set_header Connection ""： 为了兼容老的协议以及防止http头中有Connection close导致的keepalive失效，这里需要及时清掉HTTP头部的Connection

[404自动跳转到首页]---------------------------------------------------------------------------------
server {
    location / {
        error_page 404 = @ops-coffee;
    }

    location @ops-coffee {
        rewrite .* / permanent;
    }
}

网站出现404页面不是特别友好，我们可以通过上边的配置在出现404之后给自动跳转到首页去

[隐藏版本号]---------------------------------------------------------------------------------
http {
    server_tokens off;
}
经常会有针对某个版本的nginx安全漏洞出现，隐藏nginx版本号就成了主要的安全优化手段之一，当然最重要的是及时升级修复漏洞

[开启HTTPS]---------------------------------------------------------------------------------
server {
    listen 443;
    server_name baidu.com;
    ssl on;
    ssl_certificate /etc/nginx/server.crt;
    ssl_certificate_key /etc/nginx/server.key;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_ciphers HIGH:!aNULL:!MD5;
}

ssl on： 开启https
ssl_certificate： 配置nginx ssl证书的路径
ssl_certificate_key： 配置nginx ssl证书key的路径
ssl_protocols： 指定客户端建立连接时使用的ssl协议版本，如果不需要兼容TSLv1，直接去掉即可
ssl_ciphers： 指定客户端连接时所使用的加密算法，你可以再这里配置更高安全的算法

[添加黑白名单]---------------------------------------------------------------------------------
白名单配置：
location /admin/ {
    allow 192.168.1.0/24;
    deny all;
}
上边表示只允许192.168.1.0/24网段的主机访问，拒绝其他所有

也可以写成黑名单的方式禁止某些地址访问，允许其他所有，例如
location /ops-coffee/ {
    deny 192.168.1.0/24;
    allow all;
}
更多的时候客户端请求会经过层层代理，我们需要通过$http_x_forwarded_for来进行限制，可以这样写

set $allow false;
if ($http_x_forwarded_for = "211.144.204.2") {
    set $allow true;
}

if ($http_x_forwarded_for ~ "108.2.66.[89]") {
    set $allow true;
}

if ($allow = false) {
    return 404;
}

[限制请求方法]---------------------------------------------------------------------------------
if ($request_method !~ ^(GET|POST)$ ) {return 405;}

$request_method能够获取到请求nginx的method
配置只允许GET\POST方法访问，其他的method返回405

[拒绝User-Agent]---------------------------------------------------------------------------------
if ($http_user_agent ~* LWP::Simple|BBBike|wget|curl) {return 444;}

可能有一些不法者会利用wget/curl等工具扫描我们的网站，我们可以通过禁止相应的user-agent来简单的防范
Nginx的444状态比较特殊，如果返回444那么客户端将不会收到服务端返回的信息，就像是网站无法连接一样

[图片防止盗链]---------------------------------------------------------------------------------
location /images/ {
    valid_referers none blocked www.baidu.com baidu.com;
    if ($invalid_referer) {
        return 403;
    }
}

valid_referers： 验证referer，其中none允许referer为空，blocked允许不带协议的请求，
除了以上两类外仅允许referer为www.baidu.com或baidu.com时访问images下的图片资源，否则返回403

当然你也可以给不符合referer规则的请求重定向到一个默认的图片，比如下边这样
location /images/ {
    valid_referers blocked www.baidu.com baidu.com;
    if ($invalid_referer) {
        rewrite ^/images/.*\.(gif|jpg|jpeg|png)$ /static/qrcode.jpg last;
    }
}

[控制并发连接数]---------------------------------------------------------------------------------
可以通过ngx_http_limit_conn_module模块限制一个IP的并发连接数

http {
    limit_conn_zone $binary_remote_addr zone=ops:10m;
    server {
        listen 80;
        server_name baidu.com;
        root /home/project/webapp;
        index index.html;
       
        location / {
            limit_conn ops 10;
        }

        access_log /tmp/nginx_access.log main;
    }
}

limit_conn_zone： 设定保存各个键(例如$binary_remote_addr)状态的共享内存空间的参数，zone=空间名字:大小

大小的计算与变量有关，例如$binary_remote_addr变量的大小对于记录IPV4地址是固定的4 bytes，而记录IPV6地址时固定的16 bytes，
存储状态在32位平台中占用32或者64 bytes，在64位平台中占用64 bytes。1m的共享内存空间可以保存大约3.2万个32位的状态，1.6万个64位的状态

limit_conn： 指定一块已经设定的共享内存空间(例如name为ops的空间)，以及每个给定键值的最大连接数

上边的例子表示同一IP同一时间只允许10个连接

当有多个limit_conn指令被配置时，所有的连接数限制都会生效

http {
    limit_conn_zone $binary_remote_addr zone=ops:10m;
    limit_conn_zone $server_name zone=coffee:10m;
    server {
        listen 80;
        server_name baidu.com;
        root /home/project/webapp;
        index index.html;
        
        location / {
            limit_conn ops 10;
            limit_conn coffee 2000;
        }
    }
}
上边的配置不仅会限制单一IP来源的连接数为10，同时也会限制单一虚拟服务器的总连接数为2000

[缓冲区溢出攻击]---------------------------------------------------------------------------------
缓冲区溢出攻击 是通过将数据写入缓冲区并超出缓冲区边界和重写内存片段来实现的，限制缓冲区大小可有效防止

client_body_buffer_size 1K;
client_header_buffer_size 1k;
client_max_body_size 1k;
large_client_header_buffers 2 1k;

client_body_buffer_size： 默认8k或16k，表示客户端请求body占用缓冲区大小。如果连接请求超过缓存区指定的值，那么这些请求实体的整体或部分将尝试写入一个临时文件。
client_header_buffer_size： 表示客户端请求头部的缓冲区大小。绝大多数情况下一个请求头不会大于1k，不过如果有来自于wap客户端的较大的cookie它可能会大于 1k，Nginx将分配给它一个更大的缓冲区，这个值可以在large_client_header_buffers里面设置
client_max_body_size： 表示客户端请求的最大可接受body大小，它出现在请求头部的Content-Length字段， 如果请求大于指定的值，客户端将收到一个"Request Entity Too Large" (413)错误，通常在上传文件到服务器时会受到限制
large_client_header_buffers 表示一些比较大的请求头使用的缓冲区数量和大小，默认一个缓冲区大小为操作系统中分页文件大小，通常是4k或8k，请求字段不能大于一个缓冲区大小，如果客户端发送一个比较大的头，nginx将返回"Request URI too large" (414)，请求的头部最长字段不能大于一个缓冲区，否则服务器将返回"Bad request" (400)

同时需要修改几个超时时间的配置
client_body_timeout 10;
client_header_timeout 10;
keepalive_timeout 5 5;
send_timeout 10;

client_body_timeout： 表示读取请求body的超时时间，如果连接超过这个时间而客户端没有任何响应，Nginx将返回"Request time out" (408)错误
client_header_timeout： 表示读取客户端请求头的超时时间，如果连接超过这个时间而客户端没有任何响应，Nginx将返回"Request time out" (408)错误
keepalive_timeout： 参数的第一个值表示客户端与服务器长连接的超时时间，超过这个时间，服务器将关闭连接，可选的第二个参数参数表示Response头中Keep-Alive: timeout=time的time值，这个值可以使一些浏览器知道什么时候关闭连接，以便服务器不用重复关闭，如果不指定这个参数，nginx不会在应Response头中发送Keep-Alive信息
send_timeout： 表示发送给客户端应答后的超时时间，Timeout是指没有进入完整established状态，只完成了两次握手，如果超过这个时间客户端没有任何响应，nginx将关闭连接

[Header头设置]---------------------------------------------------------------------------------
通过以下设置可有效防止XSS攻击

add_header X-Frame-Options "SAMEORIGIN";
add_header X-XSS-Protection "1; mode=block";
add_header X-Content-Type-Options "nosniff";

X-Frame-Options： 响应头表示是否允许浏览器加载frame等属性，有三个配置DENY禁止任何网页被嵌入,SAMEORIGIN只允许本网站的嵌套,ALLOW-FROM允许指定地址的嵌套
X-XSS-Protection： 表示启用XSS过滤（禁用过滤为X-XSS-Protection: 0），mode=block表示若检查到XSS攻击则停止渲染页面
X-Content-Type-Options： 响应头用来指定浏览器对未指定或错误指定Content-Type资源真正类型的猜测行为，nosniff 表示不允许任何猜测

在通常的请求响应中，浏览器会根据Content-Type来分辨响应的类型，但当响应类型未指定或错误指定时，
浏览会尝试启用MIME-sniffing来猜测资源的响应类型，这是非常危险的
例如一个.jpg的图片文件被恶意嵌入了可执行的js代码，在开启资源类型猜测的情况下，浏览器将执行嵌入的js代码，可能会有意想不到的后果

另外还有几个关于请求头的安全配置需要注意

Content-Security-Policy： 定义页面可以加载哪些资源，
add_header Content-Security-Policy "default-src 'self'";

上边的配置会限制所有的外部资源，都只能从当前域名加载，其中default-src定义针对所有类型资源的默认加载策略，self允许来自相同来源的内容

Strict-Transport-Security： 会告诉浏览器用HTTPS协议代替HTTP来访问目标站点
add_header Strict-Transport-Security "max-age=31536000; includeSubDomains";

上边的配置表示当用户第一次访问后，会返回一个包含了Strict-Transport-Security响应头的字段，这个字段会告诉浏览器，
在接下来的31536000秒内，当前网站的所有请求都使用https协议访问，参数includeSubDomains是可选的，表示所有子域名也将采用同样的规则
