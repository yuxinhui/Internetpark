package com.jinfukeji.internetindustrialpark.been;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"10:46"
 * 包名:com.jinfukeji.internetindustrialpark.been
 * 描述:招商项目实例
 */

public class ZhaoshangXiangmuBeen {
    /**
     * iTotalDisplayRecords : 11
     * message : [{"id":"1","pcode":"1","pname":"贵金属","pid":"11","category":{"id":"1","code":"11","name":"互联网金融","pic":null,"description":null,"project":null}},{"id":"fb3dba9b-f7da-4546-93b3-685c0b98433d","pcode":"2","pname":"微盘","pid":"11","category":{"id":"fb3dba9b-f7da-4546-93b3-685c0b98433d","code":"11","name":"互联网金融","pic":null,"description":null,"project":null}}]
     * status : ok
     * iTotalRecords : 11
     * aaData : [{"id":"fb3dba9b-f7da-4546-93b3-685c0b98433d","pcode":"2","pname":"微盘","pid":"11","category":{"id":"fb3dba9b-f7da-4546-93b3-685c0b98433d","code":"11","name":"互联网金融","pic":null,"description":null,"project":null}}]
     * sEcho : 1
     */

    private int iTotalDisplayRecords;
    private String status;
    private int iTotalRecords;
    private String sEcho;
    private List<MessageBean> message;
    private List<AaDataBean> aaData;

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getSEcho() {
        return sEcho;
    }

    public void setSEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class MessageBean {
        /**
         * id : 1
         * pcode : 1
         * pname : 贵金属
         * pid : 11
         * category : {"id":"1","code":"11","name":"互联网金融","pic":null,"description":null,"project":null}
         */

        private String id;
        private String pcode;
        private String pname;
        private String pid;
        private CategoryBean category;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * id : 1
             * code : 11
             * name : 互联网金融
             * pic : null
             * description : null
             * project : null
             */

            private String id;
            private String code;
            private String name;
            private Object pic;
            private Object description;
            private Object project;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getProject() {
                return project;
            }

            public void setProject(Object project) {
                this.project = project;
            }
        }
    }

    public static class AaDataBean {
        /**
         * id : fb3dba9b-f7da-4546-93b3-685c0b98433d
         * pcode : 2
         * pname : 微盘
         * pid : 11
         * category : {"id":"fb3dba9b-f7da-4546-93b3-685c0b98433d","code":"11","name":"互联网金融","pic":null,"description":null,"project":null}
         */

        private String id;
        private String pcode;
        private String pname;
        private String pid;
        private CategoryBeanX category;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public CategoryBeanX getCategory() {
            return category;
        }

        public void setCategory(CategoryBeanX category) {
            this.category = category;
        }

        public static class CategoryBeanX {
            /**
             * id : fb3dba9b-f7da-4546-93b3-685c0b98433d
             * code : 11
             * name : 互联网金融
             * pic : null
             * description : null
             * project : null
             */

            private String id;
            private String code;
            private String name;
            private Object pic;
            private Object description;
            private Object project;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getProject() {
                return project;
            }

            public void setProject(Object project) {
                this.project = project;
            }
        }
    }
}
