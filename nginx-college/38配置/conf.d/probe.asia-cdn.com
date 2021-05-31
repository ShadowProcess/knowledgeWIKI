    log_format probe '$remote_addr - $remote_user [$time_local] "$request" '
           '$status $body_bytes_sent "$http_referer" '
           '"$http_user_agent" $http_x_forwarded_for';

server {
        listen       80;
        server_name  probe.asia-cdn.com;
        charset utf-8;
         index index.html index.htm;

        #allow  210.22.180.234;
        #deny    all;

        access_log  /data/0/logs/nginx/probe.asia-cdn.com.log  probe;

	rewrite ^/(.*) /aa/$1 redirect; 
        #location /
        #{
        #proxy_pass http://192.168.1.10:3002;
        #proxy_set_header   Host             $host;
        #proxy_set_header   X-Real-IP        $remote_addr;
        #proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        #}
}
