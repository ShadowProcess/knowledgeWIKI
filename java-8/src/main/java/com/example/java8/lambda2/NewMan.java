package com.example.java8.lambda2;

import java.util.Optional;

//注意：Optional 不能被序列化
public class NewMan {

	private Optional<Goddess> goddess = Optional.empty();
	
	private Goddess god;
	
	public Optional<Goddess> getGod(){
		return Optional.of(god);
	}

	public NewMan() {
	}

	public NewMan(Optional<Goddess> goddess) {
		this.goddess = goddess;
	}

	public Optional<Goddess> getGoddess() {
		return goddess;
	}

	public void setGoddess(Optional<Goddess> goddess) {
		this.goddess = goddess;
	}

	@Override
	public String toString() {
		return "NewMan [godness=" + goddess + "]";
	}

}
