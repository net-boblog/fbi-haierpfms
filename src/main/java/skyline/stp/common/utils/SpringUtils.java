package skyline.stp.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XIANGYANG on 2015-8-10.
 */

public final class SpringUtils implements BeanFactoryPostProcessor {

	private static ConfigurableListableBeanFactory beanFactory; // SpringӦ�������Ļ���

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param name
	 * @return Object һ������������ע���bean��ʵ��
	 * @throws BeansException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	/**
	 * ��ȡ����ΪrequiredType�Ķ���
	 * 
	 * @param clz
	 * @return
	 * @throws BeansException
	 * 
	 */
	public static <T> T getBean(Class<T> clz) throws BeansException {
		@SuppressWarnings("unchecked")
		T result = (T) beanFactory.getBean(clz);
		return result;
	}

	/**
	 * ���BeanFactory����һ������������ƥ���bean���壬�򷵻�true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name);
	}

	/**
	 * �ж��Ը�������ע���bean������һ��singleton����һ��prototype��
	 * ��������������Ӧ��bean����û�б��ҵ��������׳�һ���쳣��NoSuchBeanDefinitionException��
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 * 
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class ע����������
	 * @throws NoSuchBeanDefinitionException
	 * 
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getType(name);
	}

	/**
	 * ���������bean������bean�������б������򷵻���Щ����
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 * 
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getAliases(name);
	}

    public static List<String> getSpecifiedPathBeanNames(String[] pathPara) throws BeansException {
        String beanClassName=null;
        List<String> beanNameList = new ArrayList<String>();
        for (String path : pathPara) {
            for (String beanName : beanFactory.getBeanDefinitionNames()) {
                beanClassName = beanFactory.getBeanDefinition(beanName).getBeanClassName();
                if (beanClassName.startsWith(path)&&!beanNameList.contains(beanName)) {
                    beanNameList.add(beanName);
                }
            }
        }
        return beanNameList;
    }

}
