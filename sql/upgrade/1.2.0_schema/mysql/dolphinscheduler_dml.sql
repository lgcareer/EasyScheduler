SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
SET FOREIGN_KEY_CHECKS=0;
UPDATE QRTZ_CRON_TRIGGERS SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';
UPDATE QRTZ_TRIGGERS SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';
UPDATE QRTZ_FIRED_TRIGGERS SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';
UPDATE QRTZ_JOB_DETAILS SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';
UPDATE QRTZ_JOB_DETAILS SET JOB_CLASS_NAME='org.apache.dolphinscheduler.server.quartz.ProcessScheduleJob' WHERE JOB_CLASS_NAME='cn.escheduler.server.quartz.ProcessScheduleJob';
UPDATE QRTZ_LOCKS SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';
UPDATE QRTZ_SCHEDULER_STATE SET SCHED_NAME='DolphinScheduler' WHERE SCHED_NAME='EasyScheduler';