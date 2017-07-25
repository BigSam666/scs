package com.zhcs.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用户发送过来的数据
 * @author xinxi
 *
 */
@XmlRootElement(name="xml")
public class ReceiveData {
	@XmlElement(name="ToUserName")
	private String ToUserName;//开发者微信号
	@XmlElement(name="FromUserName")
	private String FromUserName;//发送方帐号（一个OpenID）
	@XmlElement(name="CreateTime")
	private String CreateTime;//消息创建时间 （整型）
	@XmlElement(name="MsgType")
	private String MsgType;//消息类型
	@XmlElement(name="Content")
	private String Content;//文本消息内容
	@XmlElement(name="MsgId")
	private String MsgId;//消息id，64位整型
	@XmlElement(name="Event")
	private String Event;//事件(关注、取消关注等)
	@XmlElement(name="PicUrl")
	private String PicUrl;//图片链接
	@XmlElement(name="MediaId")
	private String MediaId;//消息媒体id，可以调用多媒体文件下载接口拉取数据。
	@XmlElement(name="ThumbMediaId")
	private String ThumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	@XmlElement(name="Location_X")
	private String Location_X;//地理位置维度
	@XmlElement(name="Location_Y")
	private String Location_Y;//地理位置经度
	@XmlElement(name="Label")
	private String Label;//地理位置信息
	@XmlElement(name="Scale")
	private String Scale;//地图缩放大小
	@XmlElement(name="Format")
	private String Format;//语音格式，如amr，speex等
	@XmlElement(name="Title")
	private String Title;//链接消息标题
	@XmlElement(name="Description")
	private String Description;//链接消息描述
	@XmlElement(name="Url")
	private String Url;//消息链接
	@XmlElement(name="EventKey")
	private String EventKey;//事件KEY值
	@XmlElement(name="ScanCodeInfo")
	private String ScanCodeInfo;//扫描信息
	@XmlElement(name="ScanType")
	private String ScanType;//扫描类型，一般是qrcode
	@XmlElement(name="ScanResult")
	private String ScanResult;//扫描结果，即二维码对应的字符串信息
	@XmlElement(name="SendLocationInfo")
	private String SendLocationInfo;//发送的位置信息
	

	@Override
	public String toString() {
		return "ReceiveData [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Content=" + Content + ", MsgId=" + MsgId + "]";
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	public String getScanType() {
		return ScanType;
	}

	public void setScanType(String scanType) {
		ScanType = scanType;
	}

	public String getScanResult() {
		return ScanResult;
	}

	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}

	public String getSendLocationInfo() {
		return SendLocationInfo;
	}

	public void setSendLocationInfo(String sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
