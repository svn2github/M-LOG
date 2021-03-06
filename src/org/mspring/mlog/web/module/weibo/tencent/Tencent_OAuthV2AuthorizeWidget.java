/**
 * 
 */
package org.mspring.mlog.web.module.weibo.tencent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.api.weibo.tencent.Const;
import org.mspring.mlog.api.weibo.tencent.oauthv2.OAuthV2;
import org.mspring.mlog.api.weibo.tencent.oauthv2.OAuthV2Client;
import org.mspring.mlog.api.weibo.tencent.service.TencentWeiboService;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.module.weibo.AbstractWeiboWidget;
import org.mspring.mlog.web.security.SecurityUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-5-22
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/weibo/tencent")
public class Tencent_OAuthV2AuthorizeWidget extends AbstractWeiboWidget {

    @Autowired
    private TencentWeiboService tencentWeiboService;
    
    @Autowired
    private OptionService optionService;

    private static OAuthV2 oAuth = new OAuthV2();

    @RequestMapping("/authorize")
    public String authorize(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = SecurityUtils.getCurrentUser(request);
        if (user == null) {
            return prompt(model, "请先登录系统！");
        }
        oAuth.setClientId(Const.APP_KEY);
        oAuth.setClientSecret(Const.APP_SECRET);
        oAuth.setRedirectUri(optionService.getOption("blogurl") + "/weibo/tencent/callback");
        String authorizationUrl = OAuthV2Client.generateAuthorizationURL(oAuth);
        return "redirect:" + authorizationUrl;
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam(required = false) String code, @RequestParam(required = false) String openid, @RequestParam(required = false) String openkey, @RequestParam(required = false) Integer state, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = SecurityUtils.getCurrentUser(request);
        if (user == null) {
            return prompt(model, "请先登录系统！");
        }

        // 获取授权失败
        if (StringUtils.isBlank(code)) {
            oAuth.setStatus(2);
            return prompt(model, "获取授权码失败！");
        }

        oAuth.setAuthorizeCode(code);
        oAuth.setOpenid(openid);
        oAuth.setOpenkey(openkey);
        oAuth.setStatus(0);

        // 换取access token
        oAuth.setGrantType(OAuthV2.GrantType.authorization_code);
        try {
            OAuthV2Client.accessToken(oAuth);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 检测是否正确获取到access token
        if (oAuth.getStatus() == 3) {
            // 获取access token失败
            return prompt(model, "获取access token失败！");
        }
        tencentWeiboService.bindTencentWeibo(user.getId(), oAuth.getAccessToken(), oAuth.getOpenid(), oAuth.getOpenkey());
        return prompt(model, "获取授权成功！");
    }

}
