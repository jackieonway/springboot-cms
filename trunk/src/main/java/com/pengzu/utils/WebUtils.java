package com.pengzu.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintStream;
import java.util.*;

public class WebUtils {

    public static final class Http {

        /**
         * Get the request IP
         *
         * @param request
         * @return
         */
        public static String getIpAddr(HttpServletRequest request) {
            String ip = "0.0.0.0";
            ip = request.getHeader("x-forwarded-for");
            if (isInvalidIP(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (isInvalidIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (isInvalidIP(ip)) {
                ip = request.getRemoteAddr();
            }
            if (isInvalidIP(ip)) { // get X-real-ip from nginx
                ip = request.getHeader("X-real-ip");
            }
            if (isInvalidIP(ip) && null != request.getAttribute("X-real-ip")) {
                ip = request.getAttribute("X-real-ip").toString();
            }
            if (null == ip) {
                ip = "unknown";
            }
            return ip;
        }

        public static boolean isInvalidIP(String ip) {
            return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "127.0.0.1".equals(ip);
        }

        /**
         * Get the base path
         *
         * @param request
         * @return
         */
        public static String getBasePath(HttpServletRequest request) {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
            return basePath;
        }

        /**
         * Get the base path haven't port
         *
         * @param request
         * @return
         */
        public static String getBasePathNotPort(HttpServletRequest request) {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + path;
            return basePath;
        }

        /**
         * Get the url
         *
         * @param request
         * @return
         */
        public static String getURL(HttpServletRequest request) {
            return request.getRequestURL().toString();
        }

        /**
         * Get the context path
         *
         * @param request
         * @return
         */
        public static String getContextPath(HttpServletRequest request) {
            String path = request.getContextPath();
            return path;
        }

        /**
         * @return
         */
        public static String getRealPath() {
            return Property.getRoot();
        }

        public static String getUserAgent(HttpServletRequest request) {
            return request.getHeader("user-agent");
        }

        public static String getMethod(HttpServletRequest request) {
            return request.getMethod();
        }
    }

    public static final class Property extends PropertyPlaceholderConfigurer {

        public static final Logger logger = Logger.getLogger(Property.class);

        private static Map<String, String> propertyMap;

        @Override
        protected void processProperties(
                ConfigurableListableBeanFactory beanFactoryToProcess,
                Properties props) throws BeansException {
            super.processProperties(beanFactoryToProcess, props);
            propertyMap = new HashMap<String, String>();
            for (Object key : props.keySet()) {
                String keyStr = key.toString();
                String value = props.getProperty(keyStr);
                propertyMap.put(keyStr, value);
            }
        }


        public static String getRoot() {
            String rootKey = "com.meallink.root";
            String cmsRoot = System.getProperty(rootKey);
            Enumeration<?> enu = System.getProperties().propertyNames();
            while (enu.hasMoreElements()) {
                Object key = enu.nextElement();
                if (key.toString().startsWith(rootKey)) {
                    cmsRoot = System.getProperty(key.toString());
                    break;
                }
            }
            logger.info(cmsRoot);
            return cmsRoot;
        }
    }

    public static final class StdOutErrRedirect {

        private final static Log logger = LogFactory
                .getLog(StdOutErrRedirect.class);

        public static void redirectSystemOutAndErrToLog() {
            PrintStream printStreamForOut = createLoggingWrapper(System.out,
                    false);
            PrintStream printStreamForErr = createLoggingWrapper(System.out,
                    true);
            System.setOut(printStreamForOut);
            System.setErr(printStreamForErr);
        }

        public static PrintStream createLoggingWrapper(
                final PrintStream printStream, final boolean isErr) {
            return new PrintStream(printStream) {
                @Override
                public void print(final String string) {
                    if (!isErr) {
                        logger.debug(string);
                    } else {
                        logger.error(string);
                    }
                }

                @Override
                public void print(boolean b) {
                    if (!isErr) {
                        logger.debug(Boolean.valueOf(b));
                    } else {
                        logger.error(Boolean.valueOf(b));
                    }
                }

                @Override
                public void print(char c) {
                    if (!isErr) {
                        logger.debug(Character.valueOf(c));
                    } else {
                        logger.error(Character.valueOf(c));
                    }
                }

                @Override
                public void print(int i) {
                    if (!isErr) {
                        logger.debug(String.valueOf(i));
                    } else {
                        logger.error(String.valueOf(i));
                    }
                }

                @Override
                public void print(long l) {
                    if (!isErr) {
                        logger.debug(String.valueOf(l));
                    } else {
                        logger.error(String.valueOf(l));
                    }
                }

                @Override
                public void print(float f) {
                    if (!isErr) {
                        logger.debug(String.valueOf(f));
                    } else {
                        logger.error(String.valueOf(f));
                    }
                }

                @Override
                public void print(double d) {
                    if (!isErr) {
                        logger.debug(String.valueOf(d));
                    } else {
                        logger.error(String.valueOf(d));
                    }
                }

                @Override
                public void print(char[] x) {
                    if (!isErr) {
                        logger.debug(x == null ? null : new String(x));
                    } else {
                        logger.error(x == null ? null : new String(x));
                    }
                }

                @Override
                public void print(Object obj) {
                    if (!isErr) {
                        logger.debug(obj);
                    } else {
                        logger.error(obj);
                    }
                }
            };
        }
    }

    public static final class Array {

        public static Long[] toLongArray(String value, String split) {
            String[] arr = value.split(split);
            Long[] array = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                array[i] = Long.valueOf(arr[i].trim());
            }
            return array;
        }

        /**
         * 将Long数组转换成"1,2,3..."的字符串格式
         *
         * @param ids
         * @return
         */
        public static String toString(Long[] ids) {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < ids.length; i++) {
                b.append(ids[i]);
                b.append(",");
            }
            b.deleteCharAt(b.lastIndexOf(","));
            return b.toString();
        }

        /**
         * 将List转换成"1,2,3..."的字符串格式
         *
         * @param list
         * @return
         */
        public static String toString(List<String> list) {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                b.append(list.get(i));
                b.append(",");
            }
            b.deleteCharAt(b.lastIndexOf(","));
            return b.toString();
        }
    }

    public static final class Session {

        private static final String SESSION_ID = "sessionId";
        private static final String DEVICE_ID = "deviceId";

        /**
         * 获取会话ID。
         */
        public static String getSessionId(HttpServletRequest request) {
            String sessionId = request.getHeader(SESSION_ID);
            if (null == sessionId) {
                Cookie[] cookies = request.getCookies();
                if ((null != cookies) && (cookies.length > 0)) {
                    for (Cookie cooky : cookies) {
                        if (SESSION_ID.equals(cooky.getName())) {
                            sessionId = cooky.getValue();
                            break;
                        }
                    }
                }
            }
            return sessionId;
        }

        public static String getDeviceId(HttpServletRequest request) {
            return request.getHeader(DEVICE_ID);
        }
    }
}