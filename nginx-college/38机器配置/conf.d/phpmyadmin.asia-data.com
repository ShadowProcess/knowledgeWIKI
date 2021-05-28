server {
    listen    80;
    listen    88;
    server_name  phpmyadmin.asia-data.com;
    index index.php index.html;
    proxy_connect_timeout 2;
    proxy_send_timeout 2;

    access_log  /data/0/logs/nginx/phpmyadmin.asia-data.com.log main;


    root           /data/0/sites/phpMyAdmin;

    location ~* \.php$ {
        root           /data/0/sites/phpMyAdmin;
        fastcgi_buffer_size 128k;
        fastcgi_buffers 8 128k;
        fastcgi_pass   127.0.0.1:9000;
        fastcgi_index  index.php;
        #fastcgi_param  SCRIPT_FILENAME  $fastcgi_script_name;
        fastcgi_param   SCRIPT_FILENAME $document_root$fastcgi_script_name;
        include        fastcgi_params;
    }

}
