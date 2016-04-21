package skyline.stp.service;


import skyline.stp.repository.model.StpScheduleJob;
import skyline.stp.repository.model.StpScheduleLog;

import java.util.List;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

public interface ScheduleJobService {

    /**
     * ��ʼ����ʱ����
     */
    public void initScheduleJob();

    /**
     * ����
     * 
     * @param scheduleJobPara
     * @return
     */
    public int insert(StpScheduleJob scheduleJobPara);

    /**
     * ֱ���޸� ֻ���޸����е�ʱ�䣬������ͬ�첽���޷��޸�
     * 
     * @param scheduleJobPara
     */
    public void update(StpScheduleJob scheduleJobPara);

    /**
     * ɾ�����´�����ʽ
     * 
     * @param scheduleJobPara
     */
    public void delUpdate(StpScheduleJob scheduleJobPara);

    /**
     * ɾ��
     * 
     * @param scheduleJobIdPara
     */
    public void delete(String scheduleJobIdPara);

    /**
     * ����һ������
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void runOnce(String scheduleJobIdPara);

    /**
     * ��ͣ����
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void pauseJob(String scheduleJobIdPara);

    /**
     * �ָ�����
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void resumeJob(String scheduleJobIdPara);

    /**
     * ��ȡ�������
     * 
     * @param scheduleJobIdPara
     * @return
     */
    public StpScheduleJob get(String scheduleJobIdPara);

    /**
     * ��ѯ�����б�
     * @return
     */
    public List<StpScheduleJob> queryList();

    /**
     * ��ȡ�����е������б�
     *
     * @return
     */
    public List<StpScheduleJob> queryExecutingJobList();

    /**
     * ����Ƿ�����������е�����
     *
     * @return
     */
    public boolean isExistsExecutingJob(StpScheduleJob scheduleJobPara);

    /**
     * ����Ƿ����ָ��������
     *
     * @return
     */
    public boolean isExistsScheduleJob(StpScheduleJob scheduleJobPara);

    /**
     * ������־ά��
     *
     * @return
     */
    public void insertLog(String operateTypePara, String jobDescPara, StpScheduleLog logPara, StpScheduleJob scheduleJobPara);

    /**
     * ������־��ѯ
     *
     * @return
     */
    public List<StpScheduleLog> quaryJobLogList(StpScheduleJob scheduleJobPara);

    /**
     * ������־��ѯ
     *
     * @return
     */
    public List<StpScheduleLog> quaryJobLogListForQry(StpScheduleLog stpScheduleLogPara);

    public void updateScheduleRelated(String descPara, StpScheduleJob scheduleJobPara);

    }
