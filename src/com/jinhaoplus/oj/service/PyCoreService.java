package com.jinhaoplus.oj.service;

public interface PyCoreService {
	//��pythonԴ����ı���
	public String compileCode(Long codeId);
	//��python���������
	public String runCode(Long codeId);
	//��python�������е�Online Judge����ķ���
	public String OJResult();
}
