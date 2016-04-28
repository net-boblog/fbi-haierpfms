package pfms.service.inv;

import org.springframework.stereotype.Service;
import pfms.repository.dao.InvZzsSyncFailMapper;
import pfms.repository.model.InvZzsSyncFailExample;
import pfms.repository.model.InvZzsSyncFailKey;

import javax.annotation.Resource;
import java.util.List;

/**
 * 增值税同步失败记录维护
 */
@Service
public class InvZzsSyncFailService {
    @Resource
    private InvZzsSyncFailMapper syncFailMapper;

    public int delete(String txnDate, String datasrc) {
        InvZzsSyncFailKey syncFailKey = new InvZzsSyncFailKey();
        syncFailKey.setTxnDate(txnDate);
        syncFailKey.setDatasrc(datasrc);
        return syncFailMapper.deleteByPrimaryKey(syncFailKey);
    }

    public void insert(String txnDate, String datasrc) {
        InvZzsSyncFailExample example = new InvZzsSyncFailExample();
        InvZzsSyncFailExample.Criteria criteria = example.createCriteria();
        criteria.andTxnDateEqualTo(txnDate);
        criteria.andDatasrcEqualTo(datasrc);
        int count = syncFailMapper.countByExample(example);
        if (count > 0) {
            return;
        }
        InvZzsSyncFailKey syncFailKey = new InvZzsSyncFailKey();
        syncFailKey.setTxnDate(txnDate);
        syncFailKey.setDatasrc(datasrc);
        syncFailMapper.insert(syncFailKey);
    }

    public List<InvZzsSyncFailKey> select(String txnDate, String datasrc) {
        InvZzsSyncFailExample example = new InvZzsSyncFailExample();
        InvZzsSyncFailExample.Criteria criteria = example.createCriteria();
        criteria.andTxnDateNotEqualTo(txnDate);
        criteria.andDatasrcEqualTo(datasrc);
        return syncFailMapper.selectByExample(example);
    }
}
