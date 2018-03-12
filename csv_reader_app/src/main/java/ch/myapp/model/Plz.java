package ch.myapp.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


/**
 * The persistent class for the ` NEW_PLZ1` database table.
 * 
 */
@Entity
@Table(name="CH_PLZ")
public class Plz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "REC_ART", length = 2)
	private String recArt;

	@Id
	@Column(name = "ONRP", length = 5)
	private int ornp;
	
	@Column(name = "BFSNR", length = 5)
	private int bfsNr;
	
	@Column(name = "PLZ_TYP", length = 2)
	private int plzTyp;
	
	@Column(name = "POSTLEITZAHL", length = 4)
	private int postLeitZahl;
	
	@Column(name = "PLZ_ZZ", length = 2)
	private String plzZz;
	
	@Column(name = "GPLZ", length = 4)
	private int gPlz;
	
	@Column(name = "ORTBEZ18", length = 18)
	private String ortBez18;
	
	@Column(name = "ORTBEZ27", length = 27)
	private String ortBez27;
	
	@Column(name = "KANTON", length = 2)
	private String kanton;
	
	@Column(name = "SPRACHCODE", length = 1)
	private int sprachCode;
	
	@Column(name = "SPRACHCODE_ABW", length = 1)
	private int sprachCodeAbw;
	
	@Column(name = "BRIEFZ_DURCH", length = 5)
	private int briefZdurch;
	
	@Column(name = "GILT_AB_DAT")
	private Date giltAbDatum;

	@Column(name = "PLZ_BRIEFZUST", length = 6)
	private int plzBriefZust;
	
	@Column(name = "PLZ_COFF", length = 1)
	private String plzCoff;

	/**
	 * @return the recArt
	 */
	public String getRecArt() {
		return recArt;
	}

	/**
	 * @param recArt the recArt to set
	 */
	public void setRecArt(String recArt) {
		this.recArt = recArt;
	}

	/**
	 * @return the ornp
	 */
	public int getOrnp() {
		return ornp;
	}

	/**
	 * @param ornp the ornp to set
	 */
	public void setOrnp(int ornp) {
		this.ornp = ornp;
	}

	/**
	 * @return the bfsNr
	 */
	public int getBfsNr() {
		return bfsNr;
	}

	/**
	 * @param bfsNr the bfsNr to set
	 */
	public void setBfsNr(int bfsNr) {
		this.bfsNr = bfsNr;
	}

	/**
	 * @return the plzTyp
	 */
	public int getPlzTyp() {
		return plzTyp;
	}

	/**
	 * @param plzTyp the plzTyp to set
	 */
	public void setPlzTyp(int plzTyp) {
		this.plzTyp = plzTyp;
	}

	/**
	 * @return the postLeitZahl
	 */
	public int getPostLeitZahl() {
		return postLeitZahl;
	}

	/**
	 * @param postLeitZahl the postLeitZahl to set
	 */
	public void setPostLeitZahl(int postLeitZahl) {
		this.postLeitZahl = postLeitZahl;
	}

	/**
	 * @return the plzZz
	 */
	public String getPlzZz() {
		return plzZz;
	}

	/**
	 * @param plzZz the plzZz to set
	 */
	public void setPlzZz(String plzZz) {
		this.plzZz = plzZz;
	}

	/**
	 * @return the gPlz
	 */
	public int getgPlz() {
		return gPlz;
	}

	/**
	 * @param gPlz the gPlz to set
	 */
	public void setgPlz(int gPlz) {
		this.gPlz = gPlz;
	}

	/**
	 * @return the ortBez18
	 */
	public String getOrtBez18() {
		return ortBez18;
	}

	/**
	 * @param ortBez18 the ortBez18 to set
	 */
	public void setOrtBez18(String ortBez18) {
		this.ortBez18 = ortBez18;
	}

	/**
	 * @return the ortBez27
	 */
	public String getOrtBez27() {
		return ortBez27;
	}

	/**
	 * @param ortBez27 the ortBez27 to set
	 */
	public void setOrtBez27(String ortBez27) {
		this.ortBez27 = ortBez27;
	}

	/**
	 * @return the kanton
	 */
	public String getKanton() {
		return kanton;
	}

	/**
	 * @param kanton the kanton to set
	 */
	public void setKanton(String kanton) {
		this.kanton = kanton;
	}

	/**
	 * @return the sprachCode
	 */
	public int getSprachCode() {
		return sprachCode;
	}

	/**
	 * @param sprachCode the sprachCode to set
	 */
	public void setSprachCode(int sprachCode) {
		this.sprachCode = sprachCode;
	}

	/**
	 * @return the sprachCodeAbw
	 */
	public int getSprachCodeAbw() {
		return sprachCodeAbw;
	}

	/**
	 * @param sprachCodeAbw the sprachCodeAbw to set
	 */
	public void setSprachCodeAbw(int sprachCodeAbw) {
		this.sprachCodeAbw = sprachCodeAbw;
	}

	/**
	 * @return the briefZdurch
	 */
	public int getBriefZdurch() {
		return briefZdurch;
	}

	/**
	 * @param briefZdurch the briefZdurch to set
	 */
	public void setBriefZdurch(int briefZdurch) {
		this.briefZdurch = briefZdurch;
	}

	/**
	 * @return the giltAbDatum
	 */
	public Date getGiltAbDatum() {
		return giltAbDatum;
	}

	/**
	 * @param giltAbDatum the giltAbDatum to set
	 */
	public void setGiltAbDatum(Date giltAbDatum) {
		this.giltAbDatum = giltAbDatum;
	}

	/**
	 * @return the plzBriefZust
	 */
	public int getPlzBriefZust() {
		return plzBriefZust;
	}

	/**
	 * @param plzBriefZust the plzBriefZust to set
	 */
	public void setPlzBriefZust(int plzBriefZust) {
		this.plzBriefZust = plzBriefZust;
	}

	/**
	 * @return the plzCoff
	 */
	public String getPlzCoff() {
		return plzCoff;
	}

	/**
	 * @param plzCoff the plzCoff to set
	 */
	public void setPlzCoff(String plzCoff) {
		this.plzCoff = plzCoff;
	}

	
}