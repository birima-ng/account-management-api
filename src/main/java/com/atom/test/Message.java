package com.atom.test;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
private String address;
private String body;
private String subscriptionId;
private Date date;
private Date dateSent;
private Integer locked;
private String protocol;
private Integer read;
private Integer status;
private String serviceCenter;
private String backupType;
private Integer type;
private String creator;
private String contentType;
private String deliveryReport;
private String messageType;
private String messageBox;
private String readReport;
private String seen;
private String textOnly;
private String subject;
private String subjectCharSet;
private String transactionId;
private ArrayList addresses;
private ArrayList parts;



public ArrayList getParts() {
	return parts;
}

public void setParts(ArrayList parts) {
	this.parts = parts;
}

public ArrayList getAddresses() {
	return addresses;
}

public void setAddresses(ArrayList addresses) {
	this.addresses = addresses;
}

public String getTransactionId() {
	return transactionId;
}

public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}

public String getSubjectCharSet() {
	return subjectCharSet;
}

public void setSubjectCharSet(String subjectCharSet) {
	this.subjectCharSet = subjectCharSet;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getTextOnly() {
	return textOnly;
}

public void setTextOnly(String textOnly) {
	this.textOnly = textOnly;
}

public String getSeen() {
	return seen;
}

public void setSeen(String seen) {
	this.seen = seen;
}

public String getReadReport() {
	return readReport;
}

public void setReadReport(String readReport) {
	this.readReport = readReport;
}

public String getMessageType() {
	return messageType;
}

public void setMessageType(String messageType) {
	this.messageType = messageType;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public String getSubscriptionId() {
	return subscriptionId;
}

public void setSubscriptionId(String subscriptionId) {
	this.subscriptionId = subscriptionId;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Date getDateSent() {
	return dateSent;
}

public void setDateSent(Date dateSent) {
	this.dateSent = dateSent;
}

public Integer getLocked() {
	return locked;
}

public void setLocked(Integer locked) {
	this.locked = locked;
}

public String getProtocol() {
	return protocol;
}

public void setProtocol(String protocol) {
	this.protocol = protocol;
}

public Integer getRead() {
	return read;
}

public void setRead(Integer read) {
	this.read = read;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public String getServiceCenter() {
	return serviceCenter;
}

public void setServiceCenter(String serviceCenter) {
	this.serviceCenter = serviceCenter;
}

public String getBackupType() {
	return backupType;
}

public void setBackupType(String backupType) {
	this.backupType = backupType;
}

public Integer getType() {
	return type;
}

public void setType(Integer type) {
	this.type = type;
}

public String getCreator() {
	return creator;
}

public void setCreator(String creator) {
	this.creator = creator;
}

public String getContentType() {
	return contentType;
}

public void setContentType(String contentType) {
	this.contentType = contentType;
}

public String getDeliveryReport() {
	return deliveryReport;
}

public void setDeliveryReport(String deliveryReport) {
	this.deliveryReport = deliveryReport;
}

public String getMessageBox() {
	return messageBox;
}

public void setMessageBox(String messageBox) {
	this.messageBox = messageBox;
}

}
