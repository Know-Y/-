package com.api.expand.unit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.hibernate.criterion.MatchMode;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Query<T> extends QueryWrapper<T> {
    private int limit = 10;
    private int offset = 0;
    //("排序列名")
    private String sort = "created_at";
    //("排序(默认倒序)")
    private boolean desc = true;
    //("时间列名")
    private String tcn = "created_at";
    //("开始时间")
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime st;
    //("结束时间")
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime et;
    //("查询列名(query column)")
    private String qc;
    //("查询列值(字符串) query value")
    private String qv;
    //("查询列值(数字) query value")
    private Long qv1;
    //("查询值匹配方式 query match mode")
    private MatchMode qm;
    //("查询列")
    private String select;
    private boolean isMake;

    public void timeWrapper() {
        if (this.tcn != null && this.st != null && this.et != null) {
            this.between(this.tcn, this.st, this.et);
        }

    }

    public void orderWrapper() {
        if (this.sort != null) {
            this.orderBy(true, !this.desc, new String[]{this.sort});
        }

    }

    public Page page() {
        this.make();
        return new Page((long)this.offset, (long)this.limit);
    }

    public void queryWrapper() {
        if (this.qc != null && this.qm != null && (this.qv != null || this.qv1 != null)) {
            if (this.qv1 != null) {
                this.eq(this.qc, this.qv1);
            } else {
                switch(this.qm) {
                    case END:
                        this.likeRight(this.qc, this.qv);
                        break;
                    case START:
                        this.likeLeft(this.qc, this.qv);
                        break;
                    case ANYWHERE:
                        this.like(this.qc, this.qv);
                        break;
                    default:
                        this.eq(this.qc, this.qv);
                }
            }
        }

    }

    public Query make() {
        if (this.isMake) {
            return this;
        } else {
            this.setMake(true);
            this.orderWrapper();
            this.queryWrapper();
            this.timeWrapper();
            this.makeExpression();
            String[] columns = this.select.split("[,]");
            if (columns.length > 0) {
                this.select(columns);
            }

            return this;
        }
    }

    protected void makeExpression() {
    }
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Query)) {
            return false;
        } else {
            Query<?> other = (Query)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else if (this.getLimit() != other.getLimit()) {
                return false;
            } else if (this.getOffset() != other.getOffset()) {
                return false;
            } else {
                label134: {
                    Object this$sort = this.getSort();
                    Object other$sort = other.getSort();
                    if (this$sort == null) {
                        if (other$sort == null) {
                            break label134;
                        }
                    } else if (this$sort.equals(other$sort)) {
                        break label134;
                    }

                    return false;
                }

                if (this.isDesc() != other.isDesc()) {
                    return false;
                } else {
                    Object this$tcn = this.getTcn();
                    Object other$tcn = other.getTcn();
                    if (this$tcn == null) {
                        if (other$tcn != null) {
                            return false;
                        }
                    } else if (!this$tcn.equals(other$tcn)) {
                        return false;
                    }

                    label119: {
                        Object this$st = this.getSt();
                        Object other$st = other.getSt();
                        if (this$st == null) {
                            if (other$st == null) {
                                break label119;
                            }
                        } else if (this$st.equals(other$st)) {
                            break label119;
                        }

                        return false;
                    }

                    Object this$et = this.getEt();
                    Object other$et = other.getEt();
                    if (this$et == null) {
                        if (other$et != null) {
                            return false;
                        }
                    } else if (!this$et.equals(other$et)) {
                        return false;
                    }

                    label105: {
                        Object this$qc = this.getQc();
                        Object other$qc = other.getQc();
                        if (this$qc == null) {
                            if (other$qc == null) {
                                break label105;
                            }
                        } else if (this$qc.equals(other$qc)) {
                            break label105;
                        }

                        return false;
                    }

                    label98: {
                        Object this$qv = this.getQv();
                        Object other$qv = other.getQv();
                        if (this$qv == null) {
                            if (other$qv == null) {
                                break label98;
                            }
                        } else if (this$qv.equals(other$qv)) {
                            break label98;
                        }

                        return false;
                    }

                    Object this$qv1 = this.getQv1();
                    Object other$qv1 = other.getQv1();
                    if (this$qv1 == null) {
                        if (other$qv1 != null) {
                            return false;
                        }
                    } else if (!this$qv1.equals(other$qv1)) {
                        return false;
                    }

                    Object this$qm = this.getQm();
                    Object other$qm = other.getQm();
                    if (this$qm == null) {
                        if (other$qm != null) {
                            return false;
                        }
                    } else if (!this$qm.equals(other$qm)) {
                        return false;
                    }

                    label77: {
                        Object this$select = this.getSelect();
                        Object other$select = other.getSelect();
                        if (this$select == null) {
                            if (other$select == null) {
                                break label77;
                            }
                        } else if (this$select.equals(other$select)) {
                            break label77;
                        }

                        return false;
                    }

                    if (this.isMake() != other.isMake()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Query;
    }
    @Override
    public int hashCode() {
        //int PRIME = true;
        int result = super.hashCode();
        result = result * 59 + this.getLimit();
        result = result * 59 + this.getOffset();
        Object $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : $sort.hashCode());
        result = result * 59 + (this.isDesc() ? 79 : 97);
        Object $tcn = this.getTcn();
        result = result * 59 + ($tcn == null ? 43 : $tcn.hashCode());
        Object $st = this.getSt();
        result = result * 59 + ($st == null ? 43 : $st.hashCode());
        Object $et = this.getEt();
        result = result * 59 + ($et == null ? 43 : $et.hashCode());
        Object $qc = this.getQc();
        result = result * 59 + ($qc == null ? 43 : $qc.hashCode());
        Object $qv = this.getQv();
        result = result * 59 + ($qv == null ? 43 : $qv.hashCode());
        Object $qv1 = this.getQv1();
        result = result * 59 + ($qv1 == null ? 43 : $qv1.hashCode());
        Object $qm = this.getQm();
        result = result * 59 + ($qm == null ? 43 : $qm.hashCode());
        Object $select = this.getSelect();
        result = result * 59 + ($select == null ? 43 : $select.hashCode());
        result = result * 59 + (this.isMake() ? 79 : 97);
        return result;
    }

    public Query() {
        this.qm = MatchMode.EXACT;
        this.select = "*";
        this.isMake = false;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public String getSort() {
        return this.sort;
    }

    public boolean isDesc() {
        return this.desc;
    }

    public String getTcn() {
        return this.tcn;
    }

    public LocalDateTime getSt() {
        return this.st;
    }

    public LocalDateTime getEt() {
        return this.et;
    }

    public String getQc() {
        return this.qc;
    }

    public String getQv() {
        return this.qv;
    }

    public Long getQv1() {
        return this.qv1;
    }

    public MatchMode getQm() {
        return this.qm;
    }

    public String getSelect() {
        return this.select;
    }

    public boolean isMake() {
        return this.isMake;
    }

    public Query<T> setLimit(final int limit) {
        this.limit = limit;
        return this;
    }

    public Query<T> setOffset(final int offset) {
        this.offset = offset;
        return this;
    }

    public Query<T> setSort(final String sort) {
        this.sort = sort;
        return this;
    }

    public Query<T> setDesc(final boolean desc) {
        this.desc = desc;
        return this;
    }

    public Query<T> setTcn(final String tcn) {
        this.tcn = tcn;
        return this;
    }

    public Query<T> setSt(final LocalDateTime st) {
        this.st = st;
        return this;
    }

    public Query<T> setEt(final LocalDateTime et) {
        this.et = et;
        return this;
    }

    public Query<T> setQc(final String qc) {
        this.qc = qc;
        return this;
    }

    public Query<T> setQv(final String qv) {
        this.qv = qv;
        return this;
    }

    public Query<T> setQv1(final Long qv1) {
        this.qv1 = qv1;
        return this;
    }

    public Query<T> setQm(final MatchMode qm) {
        this.qm = qm;
        return this;
    }

    public Query<T> setSelect(final String select) {
        this.select = select;
        return this;
    }

    public Query<T> setMake(final boolean isMake) {
        this.isMake = isMake;
        return this;
    }

    public String toString() {
        int var10000 = this.getLimit();
        return "Query(limit=" + var10000 + ", offset=" + this.getOffset() + ", sort=" + this.getSort() + ", desc=" + this.isDesc() + ", tcn=" + this.getTcn() + ", st=" + this.getSt() + ", et=" + this.getEt() + ", qc=" + this.getQc() + ", qv=" + this.getQv() + ", qv1=" + this.getQv1() + ", qm=" + this.getQm() + ", select=" + this.getSelect() + ", isMake=" + this.isMake() + ")";
    }
}
