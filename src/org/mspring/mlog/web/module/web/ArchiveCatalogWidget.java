/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.web.freemarker.FreemarkerVariableNames;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO 根据Catalog归档
 */
@Widget
@RequestMapping("/")
public class ArchiveCatalogWidget extends AbstractWebWidget {
    @Autowired
    private PostService postService;

    @Autowired
    private CatalogService catalogService;

    @RequestMapping("/catalog")
    public String archiveByCatalog(@ModelAttribute Page<Post> postPage, @RequestParam(required = false) Long catalogId, HttpServletRequest request, HttpServletResponse response, Model model) {
        postService.findPostByCatalog(postPage, catalogId);
        model.addAttribute(FreemarkerVariableNames.POST_PAGE, postPage);

        // 这个catalogId是判断是否是分类归档的依据
        model.addAttribute("catalogId", catalogId);
        model.addAttribute("catalog", catalogService.getCatalogById(catalogId));

        // 设置当前页
        Catalog cat = catalogService.getCatalogById(catalogId);
        model.addAttribute(FreemarkerVariableNames.CATALOG_ARCHIVE_NAME, cat.getName());
        setCurrnetPage(model, PageNames.CATALOG_ARCHIVE);
        return "skin:/archiveCatalog";
    }
}
