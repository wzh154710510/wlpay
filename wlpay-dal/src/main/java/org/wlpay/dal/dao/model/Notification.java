package org.wlpay.dal.dao.model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private Integer id;

    private String mchID;

    private String channel;

    private String userPayName;

    private String amount;

    private Date listenerTime;

    private String notifiTiTle;

    private String notifiText;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMchID() {
        return mchID;
    }

    public void setMchID(String mchID) {
        this.mchID = mchID;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserPayName() {
        return userPayName;
    }

    public void setUserPayName(String userPayName) {
        this.userPayName = userPayName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getListenerTime() {
        return listenerTime;
    }

    public void setListenerTime(Date listenerTime) {
        this.listenerTime = listenerTime;
    }

    public String getNotifiTiTle() {
        return notifiTiTle;
    }

    public void setNotifiTiTle(String notifiTiTle) {
        this.notifiTiTle = notifiTiTle;
    }

    public String getNotifiText() {
        return notifiText;
    }

    public void setNotifiText(String notifiText) {
        this.notifiText = notifiText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mchID=").append(mchID);
        sb.append(", channel=").append(channel);
        sb.append(", userPayName=").append(userPayName);
        sb.append(", amount=").append(amount);
        sb.append(", listenerTime=").append(listenerTime);
        sb.append(", notifiTiTle=").append(notifiTiTle);
        sb.append(", notifiText=").append(notifiText);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Notification other = (Notification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMchID() == null ? other.getMchID() == null : this.getMchID().equals(other.getMchID()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getUserPayName() == null ? other.getUserPayName() == null : this.getUserPayName().equals(other.getUserPayName()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getListenerTime() == null ? other.getListenerTime() == null : this.getListenerTime().equals(other.getListenerTime()))
            && (this.getNotifiTiTle() == null ? other.getNotifiTiTle() == null : this.getNotifiTiTle().equals(other.getNotifiTiTle()))
            && (this.getNotifiText() == null ? other.getNotifiText() == null : this.getNotifiText().equals(other.getNotifiText()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMchID() == null) ? 0 : getMchID().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getUserPayName() == null) ? 0 : getUserPayName().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getListenerTime() == null) ? 0 : getListenerTime().hashCode());
        result = prime * result + ((getNotifiTiTle() == null) ? 0 : getNotifiTiTle().hashCode());
        result = prime * result + ((getNotifiText() == null) ? 0 : getNotifiText().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}