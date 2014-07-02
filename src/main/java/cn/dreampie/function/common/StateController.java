package cn.dreampie.function.common;

import cn.dreampie.common.config.AppConstants;
import cn.dreampie.common.utils.ValidateUtils;
import cn.dreampie.common.web.controller.Controller;
import com.jfinal.plugin.ehcache.CacheName;

/**
 * Created by wangrenhui on 14-1-3.
 */
public class StateController extends Controller {

    public void index() {
        dynaRender("/view/index.ftl");
    }

    @CacheName(AppConstants.DEFAULT_CACHENAME)
    public void own() {
        setAttr("states", State.dao.findBy("`state`.deleted_at is NULL"));
        dynaRender("/view/index.ftl");
    }

    @CacheName(AppConstants.DEFAULT_CACHENAME)
    public void one() {
        String type = getPara("state.type");
        String value = getPara("state.value");
        if (!ValidateUtils.me().isNullOrEmpty(type) && !ValidateUtils.me().isNullOrEmpty(value) && ValidateUtils.me().isPositiveNumber(value)) {
            setAttr("state", State.dao.findFirstBy("`state`.type=? AND `state`.value=?", type, value));
        }
        dynaRender("/view/index.ftl");
    }
}
