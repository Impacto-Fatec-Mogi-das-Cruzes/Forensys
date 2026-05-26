package com.forensys.core.command.concrete.go.strategy;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;

public class GoParentStrategy implements GoStrategy {

	@Override
	public CommandOutput execute() {
		ApplicationContext.getInstance().restoreDirectory();

		return CommandOutput.builder()
				.text("Moved to parent directory")
				.exitCode(CommandExitCode.SUCCESS)
				.build();
	}
}