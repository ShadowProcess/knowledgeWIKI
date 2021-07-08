

INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '计费管理', 'portal:bill_$$', 'portal:*', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '计费导航栏', 'portal:bill:view', 'portal:bill_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', 'CDN', 'portal:bill:operation:cdn', 'portal:bill_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '专线', 'portal:bill:operation:line', 'portal:bill_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '云存', 'portal:bill:operation:oss', 'portal:bill_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '账单列表', 'portal:bill:operation:list', 'portal:bill:view', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '账单详情', 'portal:bill:operation:details', 'portal:bill:view', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '跳转详细账单', 'portal:bill:operation:list:link', 'portal:bill:operation:list', 0);


INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '日志分析API', 'portal:logApi:view_$$', 'portal:*', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '独立IP', 'portal:logApi:view:ip', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '状态码', 'portal:logApi:view:status', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '访问URL', 'portal:logApi:view:top', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '访问来源', 'portal:logApi:view:reffer', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '命中率', 'portal:logApi:view:hit', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '总请求数', 'portal:logApi:view:general', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '日志列表查询', 'portal:logApi:view:download', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '预热API', 'portal:prefetchApi:view_$$', 'portal:*', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '文件预热', 'portal:prefetchApi:view:push', 'portal:prefetchApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '预热查询', 'portal:prefetchApi:view:task', 'portal:prefetchApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '预热导航栏', 'portal:prefetchApi:view', 'portal:prefetchApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '日志分析导航栏', 'portal:logApi:view', 'portal:logApi:view_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '开发者中心', 'portal:apiOpen:view', 'portal:*', 0);

INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '主机管理导航栏', 'portal:cloud:view', 'portal:cloud_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '实例列表导航栏', 'portal:cloud:view:cloudHostList', 'portal:cloud:cloudHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '专用服务器导航栏', 'portal:cloud:view:specialHostList', 'portal:cloud:specialHostList_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '主机管理', 'portal:cloud_$$', 'portal:*', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '业务概览', 'portal:cloud:operation:overHost', 'portal:cloud_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '实例列表', 'portal:cloud:cloudHostList_$$', 'portal:cloud_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '导出', 'portal:cloud:operation:cloudHostList:exportexc', 'portal:cloud:cloudHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '编辑资源名称', 'portal:cloud:operation:cloudHostList:editName', 'portal:cloud:cloudHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '详情', 'portal:cloud:operation:cloudHostList:detail', 'portal:cloud:cloudHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '专用服务器', 'portal:cloud:specialHostList_$$', 'portal:cloud_$$', 1);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '导出', 'portal:cloud:operation:specialHostList:exportexc', 'portal:cloud:specialHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '编辑资源名称', 'portal:cloud:operation:specialHostList:editName', 'portal:cloud:specialHostList_$$', 0);
INSERT INTO `app_permission`( `app_name`, `permission_name`, `permission_code`, `permission_parent`, `marking`) VALUES ('portal', '详情', 'portal:cloud:operation:specialHostList:detail', 'portal:cloud:specialHostList_$$', 0);




