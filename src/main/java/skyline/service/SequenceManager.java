package skyline.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.repository.model.Ptsequence;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sequence number manager
 * zhanrui  2012/03/10
 */
public class SequenceManager {
    private static final Logger logger = LoggerFactory.getLogger(SequenceManager.class);
    private static ConcurrentHashMap<String, Sequence> sequences = new ConcurrentHashMap<String, Sequence>();
    private static SequenceManager sequenceManager = null;
    private NamedParameterJdbcTemplate skylineJdbc;

    public NamedParameterJdbcTemplate getSkylineJdbc() {
        return skylineJdbc;
    }

    public void setSkylineJdbc(NamedParameterJdbcTemplate skylineJdbc) {
        this.skylineJdbc = skylineJdbc;
    }

    private SequenceManager() {
    }


    public synchronized static SequenceManager getInstance(NamedParameterJdbcTemplate skylineJdbc) {
        if (sequenceManager == null) {
            sequenceManager = new SequenceManager();
            sequenceManager.init(skylineJdbc);
        }
        return sequenceManager;
    }

    //TODO need test
    public synchronized void reload(NamedParameterJdbcTemplate skylineJdbc){
        init(skylineJdbc);
    }

    private void init(NamedParameterJdbcTemplate skylineJdbc) {
        List<Ptsequence> ptsequenceList = skylineJdbc.query("SELECT * FROM ptsequence", new HashMap<>(), new BeanPropertyRowMapper<Ptsequence>(Ptsequence.class));

        for (Ptsequence s : ptsequenceList) {
            Sequence sequence = new Sequence(s.getSeqname(), skylineJdbc);
            sequence.setMinValue(s.getMinvalue());
            sequence.setMaxValue(s.getMaxvalue());
            sequence.setCycle(s.getCycle());
            sequence.setStepValue(s.getStepvalue());
            sequence.setCache(s.getStepvalue() * s.getCache());

            //20130617 zhanrui add year support
            sequence.setUpdateYear(s.getYear());
            sequences.put(s.getSeqname(), sequence);
        }
    }

    public long nextID(String type) {
        Sequence sequence = sequences.get(type);
        if (sequence == null) {
            throw new RuntimeException("The sequence name not found!");
        }
        return sequence.nextUniqueID();
    }
}
