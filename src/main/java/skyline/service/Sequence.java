package skyline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.repository.model.Ptsequence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Sequence Number bean
 * zhanrui
 * 2012/3/10
 */
public class Sequence {
    private static final Logger logger = LoggerFactory.getLogger(Sequence.class);

    private String seqName;
    private long curValue;
    private long minValue;
    private long maxValue;
    private int stepValue;
    private String cycle;
    private int cache;
    private String updateYear; //更新年份

    private long tempMaxValue;
    private NamedParameterJdbcTemplate skylineJdbc;

    public Sequence(String type, NamedParameterJdbcTemplate skylineJdbc) {
        this.seqName = type;
        this.curValue = 0L;
        this.tempMaxValue = 0L;
        this.skylineJdbc = skylineJdbc;
    }

    public synchronized long nextUniqueID() {
        if (updateYear == null) {
            return getNextID();
        } else {
            String currYear = new SimpleDateFormat("yyyy").format(new Date());
            if (currYear.equals(updateYear)) {
                return getNextID();
            } else {
                initSeqIDWhenNewYear(5, currYear);
                long id = curValue;
                curValue += stepValue;
                return id;
            }
        }
    }

    private long getNextID() {
        if (!(curValue < tempMaxValue)) {
            getNextBlock(5);
        }
        long id = curValue;
        curValue += stepValue;
        return id;
    }

    private void getNextBlock(int count) {
        if (count == 0) {
            logger.error("Get an ID failed!");
            return;
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("seqName", seqName);
        Ptsequence ptsequence = (Ptsequence) skylineJdbc.queryForObject("SELECT * FROM ptsequence WHERE seqName=:seqName", paramMap, new BeanPropertyRowMapper<Ptsequence>(Ptsequence.class));

        if (ptsequence == null) {
            throw new RuntimeException("Don't found seqname=" + seqName);
        }

        long currentId = ptsequence.getCurvalue();
        long newId = currentId + cache;

        paramMap.put("currentId", currentId);
        paramMap.put("newId", newId);
        int result = skylineJdbc.update("UPDATE ptsequence SET curValue=:newId WHERE seqName=:seqName AND curValue=:currentId", paramMap);
        if (result == 1) {//success
            this.curValue = currentId;
            this.tempMaxValue = newId;
            if (tempMaxValue > maxValue && cycle.equals("1")) {
                this.curValue = minValue;
                this.tempMaxValue = minValue;
            }
        } else {
            logger.error("Get an ID failed! trying again...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                //
            }
            getNextBlock(count - 1);
        }
    }


    private void initSeqIDWhenNewYear(int count, String newYear) {
        if (count == 0) {
            System.err.println("Failed at last attempt to obtain an ID, aborting...");
            return;
        }
        long currentId = 1L;
        long newId = currentId + cache;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("seqName", seqName);

        paramMap.put("year", newYear);
        paramMap.put("newId", newId);

        int result = skylineJdbc.update("UPDATE ptsequence SET curValue=:newId, year=:year WHERE seqName=:seqName", paramMap);
        if (result == 1) {//success
            this.curValue = currentId;
            this.tempMaxValue = newId;
            this.updateYear = newYear;
        } else {
            logger.error("Get an ID failed! trying again...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                //
            }
            initSeqIDWhenNewYear(count - 1, newYear);
        }
    }

    //==============================================================
    public void setStepValue(int increment) {
        this.stepValue = increment;
    }

    public void setMinValue(long value) {
        minValue = value;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public void setMaxValue(long value) {
        maxValue = value;
    }

    public void setUpdateYear(String updateYear) {
        this.updateYear = updateYear;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}