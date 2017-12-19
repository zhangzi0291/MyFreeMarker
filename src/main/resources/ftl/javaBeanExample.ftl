package ${packageName}.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ${Example};
import ${Page};

public class ${className}Example implements Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ${className}Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    public String getOrderByClause() {
        return orderByClause;
    }
    
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }
    
    public boolean isDistinct() {
        return distinct;
    }
    
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }
    
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }
    
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }
    
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }
    
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
    
    public void setPage(Page page) {
        this.page=page;
    }
    
    public Page getPage() {
        return page;
    }

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
        
        <#list attrs as attr>
        public Criteria and${attr.upperCamel}IsNull() {
            addCriterion("${attr.underLine} is null");
            return (Criteria) this;
        }
        public Criteria and${attr.upperCamel}IsNotNull() {
            addCriterion("${attr.underLine} is not null");
            return (Criteria) this;
        }
        public Criteria and${attr.upperCamel}EqualTo(${attr.type!} value) {
            addCriterion("${attr.underLine} =", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}NotEqualTo(${attr.type!} value) {
            addCriterion("${attr.underLine} <>", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}GreaterThan(${attr.type!} value) {
            addCriterion("${attr.underLine} >", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}GreaterThanOrEqualTo(${attr.type!} value) {
            addCriterion("${attr.underLine} >=", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}LessThan(${attr.type!} value) {
            addCriterion("${attr.underLine} <", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}LessThanOrEqualTo(${attr.type!} value) {
            addCriterion("${attr.underLine} <=", value, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}In(List<${attr.type!}> values) {
            addCriterion("${attr.underLine} in", values, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}NotIn(List<${attr.type!}> values) {
            addCriterion("${attr.underLine} not in", values, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}Between(${attr.type!} value1, ${attr.type!} value2) {
            addCriterion("${attr.underLine} between", value1, value2, "${attr.camel}");
            return (Criteria) this;
        }

        public Criteria and${attr.upperCamel}NotBetween(${attr.type!} value1, ${attr.type!} value2) {
            addCriterion("${attr.underLine} not between", value1, value2, "${attr.camel}");
            return (Criteria) this;
        }
        </#list>
	}



	public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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