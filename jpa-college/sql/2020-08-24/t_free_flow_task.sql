ALTER TABLE `t_free_flow_task` ADD COLUMN `priority` INT DEFAULT 0 COMMENT '任务优先级,大的优先级高';
ALTER TABLE `t_free_flow_task` ADD COLUMN `middle` DATETIME COMMENT '任务开始时间';
ALTER TABLE `t_free_flow_task` ADD COLUMN `top` INT(1) DEFAULT 0 COMMENT '是否置顶,默认不置顶';
ALTER TABLE `t_free_flow_task` ADD COLUMN `task_audit_state` INT DEFAULT 0 COMMENT '任务审核状态: 0(未审核),1(已审核)';