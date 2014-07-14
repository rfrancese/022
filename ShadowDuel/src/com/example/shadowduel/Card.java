package com.example.shadowduel;

public class Card {

	private int id;
	private String name;
	private	String type;
	//private int dna;
	//private int evolp;
	private int att;
	private int dif;
	private int idimg;
	

	public Card(int idimg,int id,String name,int att,int dif,String type) {
		// TODO Auto-generated constructor stub
		this.idimg=idimg;
		this.id=id;
		this.name=name;
		this.type=type;
		//this.dna=dna;
		//this.evolp=evolp;
		this.att=att;
		this.dif=dif;
	}
	public int getIdImg() {
		return this.idimg;
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
//	public int getDna() {
//		return this.dna;
//	}
//	public int getEvolp() {
//		return this.evolp;
//	}
	public int getAtt() {
		return this.att;
	}
	public int getDif() {
		return this.dif;
	}
	
}
