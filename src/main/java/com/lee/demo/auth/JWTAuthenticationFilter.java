package com.lee.demo.auth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author xxm
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        String header = request.getHeader(JwtUtils.AUTHORIZATION);

        JsonObject json=new JsonObject();
        //跳过不需要验证的路径
        if(Arrays.asList(SpringSecurityConfig.AUTH_WHITELIST).contains(url)){
            chain.doFilter(request, response);
            return;
        }
        if (header==null || !header.startsWith(JwtUtils.TOKEN_PREFIX)) {
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token is null");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }catch (ExpiredJwtException e) {
            //json.addProperty("status", "-2");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token is Expired");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("Token已过期: {} " + e);
        } catch (UnsupportedJwtException e) {
            //json.addProperty("status", "-3");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token 格式错误");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("Token格式错误: {} " + e);
        } catch (MalformedJwtException e) {
            //json.addProperty("status", "-4");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token没有被正确构造");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("Token没有被正确构造: {} " + e);
        } catch (SignatureException e) {
            //json.addProperty("status", "-5");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token signature exception");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("签名失败: {} " + e);
        } catch (IllegalArgumentException e) {
            //json.addProperty("status", "-6");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Token illegal argument exception");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("非法参数异常: {} " + e);
        }catch (Exception e){
            //json.addProperty("status", "-9");
            json.addProperty("codeCheck", false);
            json.addProperty("msg", "Invalid Token");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(json));
            logger.error("Invalid Token " + e.getMessage());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response)  {
        String token = request.getHeader(JwtUtils.AUTHORIZATION);
        if (token != null) {
            String userId="";

            try {
                // 解密Token
                userId = JwtUtils.validateToken(token);
                if (userId!=null && userId.length()>0) {
                    return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                }
            }catch (ExpiredJwtException e) {
                throw e;
                //throw new TokenException("Token已过期");
            } catch (UnsupportedJwtException e) {
                throw e;
                //throw new TokenException("Token格式错误");
            } catch (MalformedJwtException e) {
                throw e;
                //throw new TokenException("Token没有被正确构造");
            } catch (SignatureException e) {
                throw e;
                //throw new TokenException("签名失败");
            } catch (IllegalArgumentException e) {
                throw e;
                //throw new TokenException("非法参数异常");
            }catch (Exception e){
                throw e;
                //throw new IllegalStateException("Invalid Token. "+e.getMessage());
            }
            return null;
        }
        return null;
    }

}
