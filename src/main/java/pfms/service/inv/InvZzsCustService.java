package pfms.service.inv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfms.repository.dao.InvZzsCustMapper;
import pfms.repository.model.InvZzsCust;
import pfms.repository.model.InvZzsCustExample;
import pfms.util.ToolUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * ��ֵ˰�ͻ�������Ϣά��
 */
@Service
public class InvZzsCustService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsCustMapper invZzsCustMapper;

    @Transactional
    public boolean insert(InvZzsCust invZzsCust) {
        invZzsCust.setCrtDate(ToolUtil.getDateDash());  // ��������YYYY-MM-DD
        invZzsCust.setCrtTime(ToolUtil.getTimeColon()); // ����ʱ��HH:mm:ss
        int count = invZzsCustMapper.insertSelective(invZzsCust);
        if (count != 1) {
            return false;
        }

        return true;
    }

    @Transactional
    public boolean update(InvZzsCust invZzsCust) {
        invZzsCust.setUpdDate(ToolUtil.getDateDash());  // �޸�����YYYY-MM-DD
        invZzsCust.setUpdTime(ToolUtil.getTimeColon()); // �޸�ʱ��HH:mm:ss
        int count = invZzsCustMapper.updateByPrimaryKeySelective(invZzsCust);
        if (count != 1) {
            return false;
        }

        return true;
    }

    public List<InvZzsCust> selectByExample(InvZzsCustExample example) {
        return invZzsCustMapper.selectByExample(example);
    }

    public int countByExample(InvZzsCustExample example) {
        return invZzsCustMapper.countByExample(example);
    }

    public boolean deleteByPrimaryKey(String khdm) {
        int count = invZzsCustMapper.deleteByPrimaryKey(khdm);
        if (count != 1) {
            return false;
        }

        return true;
    }
}
