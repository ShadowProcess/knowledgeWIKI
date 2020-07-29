package com.example.java8.lambda2;

import java.util.Optional;

//注意：Optional 不能被序列化
public class NewMan {

	private Optional<Goddess> godness = Optional.empty();
	
	private Goddess god;
	
	public Optional<Goddess> getGod(){
		return Optional.of(god);
	}

	public NewMan() {
	}

	public NewMan(Optional<Goddess> godness) {
		this.godness = godness;
	}

	public Optional<Goddess> getGodness() {
		return godness;
	}

	public void setGodness(Optional<Goddess> godness) {
		this.godness = godness;
	}

	@Override
	public String toString() {
		return "NewMan [godness=" + godness + "]";
	}

}
