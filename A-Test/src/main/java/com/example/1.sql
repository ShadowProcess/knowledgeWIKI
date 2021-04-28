insert into t_log_http_status_minute5 (domain, status, request, record_time, provider)
values ('vs.wshareit.com', '5xx', '0', '2021-04-27 10:20:00', 'gb')
       ON DUPLICATE KEY UPDATE
       status = VALUES (status),
       request = VALUES (request),
       record_time = VALUES (record_time),
       provider = VALUES (provider);
