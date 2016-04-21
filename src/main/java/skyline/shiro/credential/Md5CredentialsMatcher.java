package skyline.shiro.credential;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.util.DigestUtils;

public class Md5CredentialsMatcher extends SimpleCredentialsMatcher {
	 protected Object getCredentials(AuthenticationToken token) {
	        return DigestUtils.md5DigestAsHex(((String) token.getCredentials()).getBytes());
	    }
}
