package com.test.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.test.untils.JasyptUtil;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;

public class EncryptionPropertyResolver implements EncryptablePropertyResolver {

	 private static final Logger logger = LoggerFactory.getLogger(EncryptionPropertyResolver.class);
	
	@Override
	public String resolvePropertyValue(String value) {
		 if (!StringUtils.hasText(value)) {
             return value;
         }
         if (value.startsWith("DES@")) {
             try {
                 return resolveDESValue(value.substring(4));
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         return value;
     }

     private String resolveDESValue(String value) throws Exception {
         logger.info("AES@ sub value :" + value);
         String riemann = JasyptUtil.decryption(value, "heweiqing");
         logger.info("riemann DES: " + riemann);
         return riemann;
     }
}
