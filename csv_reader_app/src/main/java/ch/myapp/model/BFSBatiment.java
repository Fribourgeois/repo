package ch.myapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BFS_Batiment database table.
 * 
 */
@Entity
@Table(name="BFS_Batiment")
@NamedQuery(name="BFSBatiment.findAll", query="SELECT b FROM BFSBatiment b")
public class BFSBatiment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=false, nullable=false, length=12)
	private int egid;

	@Column(nullable=false, length=10)
	private String deinr;

	private int edid;

	@Column(nullable=false, length=2)
	private String gdekt;

	@Column(nullable=false, length=45)
	private String gdename;

	@Column(nullable=false)
	private int gdenr;

	@Column(nullable=false, length=20)
	private String gkode;

	@Column(nullable=false, length=15)
	private String gkodn;

	@Column(nullable=false)
	private int plz4;

	@Column(length=100)
	private String plzname;

	@Column(nullable=false)
	private int plzz;

	@Column(nullable=false, length=100)
	private String strname;

	@Column(nullable=false, length=10)
	private String strsp;

	public BFSBatiment() {
	}

	public int getEgid() {
		return this.egid;
	}

	public void setEgid(int egid) {
		this.egid = egid;
	}

	public String getDeinr() {
		return this.deinr;
	}

	public void setDeinr(String deinr) {
		this.deinr = deinr;
	}

	public int getEdid() {
		return this.edid;
	}

	public void setEdid(int edid) {
		this.edid = edid;
	}

	public String getGdekt() {
		return this.gdekt;
	}

	public void setGdekt(String gdekt) {
		this.gdekt = gdekt;
	}

	public String getGdename() {
		return this.gdename;
	}

	public void setGdename(String gdename) {
		this.gdename = gdename;
	}

	public int getGdenr() {
		return this.gdenr;
	}

	public void setGdenr(int gdenr) {
		this.gdenr = gdenr;
	}

	public String getGkode() {
		return this.gkode;
	}

	public void setGkode(String gkode) {
		this.gkode = gkode;
	}

	public String getGkodn() {
		return this.gkodn;
	}

	public void setGkodn(String gkodn) {
		this.gkodn = gkodn;
	}

	public int getPlz4() {
		return this.plz4;
	}

	public void setPlz4(int plz4) {
		this.plz4 = plz4;
	}

	public String getPlzname() {
		return this.plzname;
	}

	public void setPlzname(String plzname) {
		this.plzname = plzname;
	}

	public int getPlzz() {
		return this.plzz;
	}

	public void setPlzz(int plzz) {
		this.plzz = plzz;
	}

	public String getStrname() {
		return this.strname;
	}

	public void setStrname(String strname) {
		this.strname = strname;
	}

	public String getStrsp() {
		return this.strsp;
	}

	public void setStrsp(String strsp) {
		this.strsp = strsp;
	}

}