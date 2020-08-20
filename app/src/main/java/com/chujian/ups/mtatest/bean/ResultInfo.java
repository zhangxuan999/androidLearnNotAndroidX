package com.chujian.ups.mtatest.bean;

import java.util.List;

public class ResultInfo {

    /**
     * result : {"total_elements":29,"mtalogs":[{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SDKActivatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SplashStartRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceServerVisitRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"AppVersionCheckRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceLoadingRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceUpdateStartedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceUpdatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SDKLoadedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisterPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisterPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisteredEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoginPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoginPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"NoticePageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GameServerListedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GameServerLoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"AccountLogoutEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleCreatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLoginRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLogoutEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayMethodConfirmedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GravitationCheckedEvent","uuid":"","client_id":"","content":""}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * total_elements : 29
         * mtalogs : [{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SDKActivatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SplashStartRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceServerVisitRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"AppVersionCheckRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceLoadingRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceUpdateStartedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"ResourceUpdatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"SDKLoadedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisterPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisterPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RegisteredEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoginPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoginPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"LoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"NoticePageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GameServerListedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GameServerLoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"AccountLogoutEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleCreatedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLoginRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLoggedinEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"RoleLogoutEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayPageDisplayRequestedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayPageDisplayedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"PayMethodConfirmedEvent","uuid":"","client_id":"","content":""},{"umid":"","user_id":"","created_at":"","is_dot":false,"client_ip":"","category":"GravitationCheckedEvent","uuid":"","client_id":"","content":""}]
         */

        private int total_elements;
        private List<MtalogsBean> mtalogs;

        public int getTotal_elements() {
            return total_elements;
        }

        public void setTotal_elements(int total_elements) {
            this.total_elements = total_elements;
        }

        public List<MtalogsBean> getMtalogs() {
            return mtalogs;
        }

        public void setMtalogs(List<MtalogsBean> mtalogs) {
            this.mtalogs = mtalogs;
        }

        public static class MtalogsBean {
            /**
             * umid :
             * user_id :
             * created_at :
             * is_dot : false
             * client_ip :
             * category : SDKActivatedEvent
             * uuid :
             * client_id :
             * content :
             */

            private String umid;
            private String user_id;
            private String created_at;
            private boolean is_dot;
            private String client_ip;
            private String category;
            private String uuid;
            private String client_id;
            private String content;

            public String getUmid() {
                return umid;
            }

            public void setUmid(String umid) {
                this.umid = umid;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public boolean isIs_dot() {
                return is_dot;
            }

            public void setIs_dot(boolean is_dot) {
                this.is_dot = is_dot;
            }

            public String getClient_ip() {
                return client_ip;
            }

            public void setClient_ip(String client_ip) {
                this.client_ip = client_ip;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getClient_id() {
                return client_id;
            }

            public void setClient_id(String client_id) {
                this.client_id = client_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
