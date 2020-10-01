package br.com.uvv.lp.threads.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Operation {

	private Integer balance;
	private Integer pull;
	private Integer numberOfPulls;
	private Long sleep;
	
}
