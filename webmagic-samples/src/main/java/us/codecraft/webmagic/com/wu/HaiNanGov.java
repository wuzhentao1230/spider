package us.codecraft.webmagic.com.wu;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.GithubRepoPageProcessor;

public class HaiNanGov implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);
    @Override
    public void process(Page page) {
        page.addTargetRequest("http://www.ccgp-hainan.gov.cn/zytz/index_2.jhtml");
//        page.addTargetRequests(page.getHtml().css("a").all());
        page.putField("name",page.getHtml().$("a","text").all());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HaiNanGov()).addUrl("http://www.ccgp-hainan.gov.cn/zytz/index_1.jhtml").addPipeline(new JsonFilePipeline("D:\\webmagic")).thread(5).run();
    }
}
