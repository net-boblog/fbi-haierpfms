package skyline.repository.model;

import java.util.ArrayList;
import java.util.List;

public class PtmenutopExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public PtmenutopExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOperidIsNull() {
            addCriterion("OPERID is null");
            return (Criteria) this;
        }

        public Criteria andOperidIsNotNull() {
            addCriterion("OPERID is not null");
            return (Criteria) this;
        }

        public Criteria andOperidEqualTo(String value) {
            addCriterion("OPERID =", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotEqualTo(String value) {
            addCriterion("OPERID <>", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidGreaterThan(String value) {
            addCriterion("OPERID >", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidGreaterThanOrEqualTo(String value) {
            addCriterion("OPERID >=", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLessThan(String value) {
            addCriterion("OPERID <", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLessThanOrEqualTo(String value) {
            addCriterion("OPERID <=", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidLike(String value) {
            addCriterion("OPERID like", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotLike(String value) {
            addCriterion("OPERID not like", value, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidIn(List<String> values) {
            addCriterion("OPERID in", values, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotIn(List<String> values) {
            addCriterion("OPERID not in", values, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidBetween(String value1, String value2) {
            addCriterion("OPERID between", value1, value2, "operid");
            return (Criteria) this;
        }

        public Criteria andOperidNotBetween(String value1, String value2) {
            addCriterion("OPERID not between", value1, value2, "operid");
            return (Criteria) this;
        }

        public Criteria andMenuidIsNull() {
            addCriterion("MENUID is null");
            return (Criteria) this;
        }

        public Criteria andMenuidIsNotNull() {
            addCriterion("MENUID is not null");
            return (Criteria) this;
        }

        public Criteria andMenuidEqualTo(String value) {
            addCriterion("MENUID =", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidNotEqualTo(String value) {
            addCriterion("MENUID <>", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidGreaterThan(String value) {
            addCriterion("MENUID >", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidGreaterThanOrEqualTo(String value) {
            addCriterion("MENUID >=", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidLessThan(String value) {
            addCriterion("MENUID <", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidLessThanOrEqualTo(String value) {
            addCriterion("MENUID <=", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidLike(String value) {
            addCriterion("MENUID like", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidNotLike(String value) {
            addCriterion("MENUID not like", value, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidIn(List<String> values) {
            addCriterion("MENUID in", values, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidNotIn(List<String> values) {
            addCriterion("MENUID not in", values, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidBetween(String value1, String value2) {
            addCriterion("MENUID between", value1, value2, "menuid");
            return (Criteria) this;
        }

        public Criteria andMenuidNotBetween(String value1, String value2) {
            addCriterion("MENUID not between", value1, value2, "menuid");
            return (Criteria) this;
        }

        public Criteria andVisitTimesIsNull() {
            addCriterion("VISIT_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andVisitTimesIsNotNull() {
            addCriterion("VISIT_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTimesEqualTo(Integer value) {
            addCriterion("VISIT_TIMES =", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesNotEqualTo(Integer value) {
            addCriterion("VISIT_TIMES <>", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesGreaterThan(Integer value) {
            addCriterion("VISIT_TIMES >", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("VISIT_TIMES >=", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesLessThan(Integer value) {
            addCriterion("VISIT_TIMES <", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesLessThanOrEqualTo(Integer value) {
            addCriterion("VISIT_TIMES <=", value, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesIn(List<Integer> values) {
            addCriterion("VISIT_TIMES in", values, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesNotIn(List<Integer> values) {
            addCriterion("VISIT_TIMES not in", values, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesBetween(Integer value1, Integer value2) {
            addCriterion("VISIT_TIMES between", value1, value2, "visitTimes");
            return (Criteria) this;
        }

        public Criteria andVisitTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("VISIT_TIMES not between", value1, value2, "visitTimes");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTMENUTOP
     *
     * @mbggenerated do_not_delete_during_merge Tue Jan 12 13:20:41 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PTMENUTOP
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}