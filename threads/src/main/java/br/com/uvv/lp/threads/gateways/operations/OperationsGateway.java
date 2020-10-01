package br.com.uvv.lp.threads.gateways.operations;

import br.com.uvv.lp.threads.domains.Account;
import br.com.uvv.lp.threads.domains.Operation;

public interface OperationsGateway {
	void spend(final Operation spender, final Account account);
	void smart(final Operation smart, final Account account);
	void save(final Operation save, final Account account);
}
