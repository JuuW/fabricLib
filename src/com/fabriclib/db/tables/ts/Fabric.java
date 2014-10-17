package com.fabriclib.db.tables.ts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fabriclib.util.DateSerializer;
import com.fabriclib.util.NullSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "FABRIC")
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class Fabric implements Serializable {

	public Fabric() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Version
	@GeneratedValue
	@Column(name = "version")
	@JsonIgnore
	private long version;

	/**
	 * Hanger No.:该项为面料挂样编号，例如：RAY-JYI-F01-00100,
	 * RAY位置代表面料品种Rayon，JYI代表供应商缩写，F01代表图书馆货架位，00100代表在货架的具体位置。
	 */
	@Column(name = "hanger_no", nullable = true, columnDefinition = "hanger_no",length = 40)
	private String hangerNo;

	/**
	 * Date:显示为录入系统资料时间。例如：2014/09/12.
	 */
	@Column(name = "input_date")
	private Date inputDate;

	/**
	 * 是否打印
	 */
	@Column(name = "print_out")
	@JsonIgnore
	private String printOut;

	/**
	 * Construction(Warp):面料经向密度。
	 */
	@Column(name = "cstructn_Warp")
	private String cstructnWarp;

	/**
	 * Construction(Weft):面料经向密度。
	 */
	@Column(name = "cstructn_weft")
	private String cstructnWeft;

	/**
	 * Yarn(Warp):面料经向纱支。
	 */
	@Column(name = "yarn_warp")
	private String yarnWarp;

	/**
	 * Yarn(Weft):面料纬向纱支。
	 */
	@Column(name = "yarn_weft")
	private String yarnWeft;

	/**
	 * Content:面料成份。例如：100%Rayon.
	 */
	@Column(name = "content")
	private String content;

	/**
	 * Status:表示面料染色状况，例如：PD表示Piece Dye,YD表示Yarn Dye,Print;
	 */
	@Column(name = "status")
	private String status;

	/**
	 * Weaving:表示面料的织法。比如：Poplin表示平纹；Oxford表示牛津纺。Twill表示斜纹。
	 */
	@Column(name = "weaving")
	private String weaving;

	/**
	 * Finishing:代表面料后整理类型。例如：Rrgular：表示常规整理；Mercerized表示丝光处理.
	 */
	@Column(name = "finishing")
	private String finishing;

	/**
	 * Width:代表面料幅宽。例如：147cm,57/8''
	 */
	@Column(name = "width")
	private String width;
	/**
	 * Weight:表示面料克重。例如：100g/sqm.
	 */
	@Column(name = "weight")
	private String weight;

	/**
	 * Article:表示供应商的品名代码。
	 */
	@Column(name = "article")
	private String article;

	/**
	 * Original Price:表示面料供应商报价。
	 */

	@Column(name = "originalPrice")
	private String originalPrice;
	/**
	 * Final Price:报给客人的价钱。（这个选项要设置权限。）
	 */

	@Column(name = "finalPrice" ,length = 12)
	private String finalPrice;

	@Column(name = "deleted" ,length = 1)
	@JsonIgnore
	private String deleted;

	public long getId() {
		return id;
	}

	public String getPrintOut() {
		return printOut;
	}

	public void setPrintOut(String printOut) {
		this.printOut = printOut;
	}

	public String getCstructnWarp() {
		return cstructnWarp;
	}

	public void setCstructnWarp(String cstructnWarp) {
		this.cstructnWarp = cstructnWarp;
	}

	public String getCstructnWeft() {
		return cstructnWeft;
	}

	public void setCstructnWeft(String cstructnWeft) {
		this.cstructnWeft = cstructnWeft;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getHangerNo() {
		return hangerNo;
	}

	public void setHangerNo(String hangerNo) {
		this.hangerNo = hangerNo;
	}

	@JsonSerialize(using = DateSerializer.class)    
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getYarnWarp() {
		return yarnWarp;
	}

	public void setYarnWarp(String yarnWarp) {
		this.yarnWarp = yarnWarp;
	}

	public String getYarnWeft() {
		return yarnWeft;
	}

	public void setYarnWeft(String yarnWeft) {
		this.yarnWeft = yarnWeft;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeaving() {
		return weaving;
	}

	public void setWeaving(String weaving) {
		this.weaving = weaving;
	}

	public String getFinishing() {
		return finishing;
	}

	public void setFinishing(String finishing) {
		this.finishing = finishing;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	@JsonSerialize(using = NullSerializer.class)  
	public String getWeight() {
		
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Fabric [id=" + id + ", version=" + version + ", hangerNo="
				+ hangerNo + ", inputDate=" + inputDate + ", printOut="
				+ printOut + ", cstructnWarp=" + cstructnWarp
				+ ", cstructnWeft=" + cstructnWeft + ", yarnWarp=" + yarnWarp
				+ ", yarnWeft=" + yarnWeft + ", content=" + content
				+ ", status=" + status + ", weaving=" + weaving
				+ ", finishing=" + finishing + ", width=" + width + ", weight="
				+ weight + ", article=" + article + ", originalPrice="
				+ originalPrice + ", finalPrice=" + finalPrice + "]";
	}


}
