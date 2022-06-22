package com.khl.gentledentalcare.utils;

import com.khl.gentledentalcare.models.Account;
import com.khl.gentledentalcare.services.ConstantsSendEmail;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author ASUS
 */
public class FunctionSendEmail {
    private static final String SECRET_KEY = "ssshhhhhhhhhhh!!!!";
    private final Account account;
    private final String titleEmail;
    private String codeVerify;

    public FunctionSendEmail(Account account, String titleEmail, String codeVerify) {
        this.account = account;
        this.titleEmail = titleEmail;
        this.codeVerify = codeVerify;
    }

    public FunctionSendEmail(Account account, String titleEmail) {
        this.account = account;
        this.titleEmail = titleEmail;
    }

    public boolean sendMailChangePassword() {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(Encrypt.decrypt(ConstantsSendEmail.MY_EMAIL, SECRET_KEY), Encrypt.decrypt(ConstantsSendEmail.MY_PASSWORD, SECRET_KEY)));
            email.setSSLOnConnect(true);
            email.setFrom(Encrypt.decrypt(ConstantsSendEmail.MY_EMAIL, SECRET_KEY));

            email.addTo(account.getUserEmail());

            email.setSubject(titleEmail);

            email.setHtmlMsg("<html><section class=\"confirm-email\">\n"
                    + "        <div class=\"container\" style=\"margin: 0 23rem; overflow: hidden; border: 1px solid #8412c0;\">\n"
                    + "            <div class=\"confirm-box\">\n"
                    + "                <div class=\"confirm-header\" style=\"background-color: #f3e6f8; width: 100%; height: 60px;\">\n"
                    + "                    <div class=\"logo-name\" style=\"margin-left: 10px;\n"
                    + "                    padding-top: 12px;\n"
                    + "                    font-size: 2rem;\n"
                    + "                    font-weight: 500;\">\n"
                    + "                        Gentle Dental Care\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            \n"
                    + "            <div class=\"confirm-img\" style=\"width: 100%;\">\n"
                    + "                <img src=\"https://img.freepik.com/free-vector/two-steps-verification-illustration-flat-design-illustration_108061-442.jpg?size=626&ext=jpg\" alt=\"Xin chao\" style=\"width: 100%;\">\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div class=\"confirm-content\" style=\"margin-bottom: 2rem; padding-left: 1rem;\">\n"
                    + "                <div class=\"content\" style=\"margin-bottom: 3rem;\">\n"
                    + "                    <h1 style=\"padding-top: 30px;\">Change Your Password</h1>\n"
                    + "                    <p style=\"padding-top: 30px; font-size: 18px;\">Dear " + account.getFullName()+ ",</p>\n"
                    + "                    <p style=\"padding-top: 30px; font-size: 18px;\">Your reset code: " + codeVerify + "</p>\n"
                    + "                    <p style=\"padding-top: 30px; font-size: 18px;\">Thank you for trusting Gentle Dental Care.</p>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "    </section></html>");

            email.setTextMsg("Your email client does not support HTML messages");
            email.send();
            return true;
        } catch (EmailException e) {
            return false;
        }
    }

    public boolean sendMailVerifyAccount() {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(Encrypt.decrypt(ConstantsSendEmail.MY_EMAIL, SECRET_KEY), Encrypt.decrypt(ConstantsSendEmail.MY_PASSWORD, SECRET_KEY)));
            email.setSSLOnConnect(true);
            email.setFrom(Encrypt.decrypt(ConstantsSendEmail.MY_EMAIL, SECRET_KEY));

            email.addTo(account.getUserEmail());

            email.setSubject(titleEmail);

            email.setHtmlMsg("<html><section class=\"confirm-email\">\n"
                    + "        <div class=\"container\" style=\"margin: 0 23rem; overflow: hidden; border: 1px solid #8412c0;\">\n"
                    + "            <div class=\"confirm-box\">\n"
                    + "                <div class=\"confirm-header\" style=\"background-color: #f3e6f8; width: 100%; height: 60px;\">\n"
                    + "                    <div class=\"logo-name\" style=\"margin-left: 10px;\n"
                    + "                    padding-top: 12px;\n"
                    + "                    font-size: 2rem;\n"
                    + "                    font-weight: 500;\">\n"
                    + "                        Gentle Dental Care\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            \n"
                    + "            <div class=\"confirm-img\" style=\"width: 100%;\">\n"
                    + "                <img src=\"https://cdn.tgdd.vn/hoi-dap/1374019/200-hinh-nen-slide-mo-dau-powerpoint-cuc-chuyen-nghiep-an83.jpg\" alt=\"Xin chao\">\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <div class=\"confirm-content\" style=\"margin-bottom: 2rem; padding-left: 1rem;\">\n"
                    + "                <div class=\"content\" style=\"margin-bottom: 3rem;\">\n"
                    + "                    <h1 style=\"padding-top: 30px;\">Verify account email <span style=\"color: rgb(113, 23, 234);\">Gentle Dental Care</span></h1>\n"
                    + "                    <p style=\"padding-top: 30px; font-size: 18px;\">Dear " + account.getFullName()+ ",</p>\n"
                    + "                    <p style=\"padding-top: 30px; font-size: 18px;\">Thank you for creating a Gentle Dental Care account.</p>\n"
                    + "                    <p style=\"font-size: 18px;\">Please click the [Confirm] button below to complete the registration process.</p>\n"
                    + "                    <p style=\"font-size: 18px;\">Failure to confirm your e-mail account within 10 minutes will result in account deletion. If so, you will have to start the membership registration process again and receive a new confirmation e-mail.</p>\n"
                    + "                </div>\n"
                    + "\n"
                    + "                <div class=\"confirm-btn\" style=\"display: flex;\n"
                    + "                justify-content: center;\n"
                    + "                align-items: center;\">\n"
                    + "                    <a href=\"http://localhost:8080/GentleDentalCare/verify?uid=" + account.getUserEmail() + "\" class=\"btn-reset\" style=\"background-color: #8412c0;\n"
                    + "                    color: white;\n"
                    + "                    border: none;\n"
                    + "                    border-radius: 2px;\n"
                    + "                    padding: 14px;\n"
                    + "                    text-decoration: none;\n"
                    + "                    font-weight: 500;\">VERIFY ACCOUNT</a>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "    </section></html>");

            email.setTextMsg("Your email client does not support HTML messages");
            email.send();
            return true;
        } catch (EmailException e) {
            return false;
        }
    }
}
