package br.com.accenture.challenger.databuilders.cases.gateways.http.controllers.task;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TaskControllerTemplateLoader implements TemplateLoader {

	public void load() {
		CreateTaskRequestJsonTemplate.load();
	}

}
