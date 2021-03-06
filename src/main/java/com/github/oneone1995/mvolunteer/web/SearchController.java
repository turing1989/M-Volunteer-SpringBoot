package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.SearchService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 * 搜索有关controller
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @GetMapping("activity")
    public ResponseEntity<?> getActivitySearchResult(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "coordLong") double coordLong,
            @RequestParam(value = "coordLat") double coordLat,
            @RequestParam(value = "activityName") String activityName
    ) {
        PageInfo<HomeActivity> searchActivityPageInfo = searchService.getHomeActivityPageInfo(
                page, rows, coordLong, coordLat, activityName);
        if (searchActivityPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(searchActivityPageInfo), HttpStatus.OK);
    }
}
