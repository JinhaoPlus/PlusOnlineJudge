package com.jinhaoplus.oj.service.langCore.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.jinhaoplus.oj.domain.CommonMessage;
import com.jinhaoplus.oj.service.langCore.LangCoreService;
import com.jinhaoplus.oj.util.PropertiesUtil;

@Service
public class CCoreServiceImpl implements LangCoreService {

	@Override
	public CommonMessage compileCode(int problemId,String path) {
		CommonMessage message;
		String fileName = path.substring(0,path.lastIndexOf("."));
		ProcessBuilder processBuilder;
        boolean compiled = true;
        processBuilder = new ProcessBuilder("gcc","-std=c++0x","-w","-o", fileName, path);
        processBuilder.redirectErrorStream(true);

        try {
            Process compileProcess = processBuilder.start();
            //Regard the shell as IO object , so we can use inputStream to read some lines from this special IO
            InputStream is = compileProcess.getInputStream();
            BufferedReader b = new BufferedReader(new InputStreamReader(is));
            StringBuffer compileInfo=new StringBuffer("");
            String compileInfoLine;
            //if correctly compiled , there should not be output in the shell , so the inputStream should be null
            while ((compileInfoLine = b.readLine()) != null) {
                compiled = false;
                compileInfo.append(compileInfoLine);
            }
            compileProcess.waitFor();

            if(compiled){
            	message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_SUCCESS_CODE"), 
            								PropertiesUtil.getProperty("COMPILE_SUCCESS"),
            								"");
            	is.close();
            }
            else{
            	message = new CommonMessage(PropertiesUtil.getProperty("COMPILE_ERROR_CODE"), 
											PropertiesUtil.getProperty("COMPILE_ERROR"),
											compileInfo.toString());
                is.close();
               
            }
            return message;
        } catch (Exception e) {
            System.out.println("in compile\n " + e);
        }
		return null;
	}

	@Override
	public String runCode(int ProblemId, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String OJResult() {
		String result="";
		try {
			result += "[run INFO] "+"AC"+"\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String createTempSourceFile(String fileOrDirName) {
		return fileOrDirName;
	}

	
}
