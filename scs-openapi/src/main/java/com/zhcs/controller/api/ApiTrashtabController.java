package com.zhcs.controller.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.entity.TrashtabDetailEntity;
import com.zhcs.entity.TrashtabEntity;
import com.zhcs.entity.TrashtabList;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.service.TrashtabService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

@Api(value = "垃圾桶标记" , description = "垃圾桶标记")
@Controller
@RequestMapping("/api/trashtab")
@CrossOrigin
public class ApiTrashtabController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TrashtabService trashtabService;
	
	@ApiOperation(value = "新增" , notes = "新增")
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	@ResponseBody
	public R add(@RequestBody TrashtabEntity trashtabEntity){
		TokenEntity token = tokenService.queryByToken(trashtabEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			if (StringUtil.isBlank(trashtabEntity.getAddr()) || StringUtil.isBlank(trashtabEntity.getLat()) || StringUtil.isBlank(trashtabEntity.getLng())) {
				return R.error(61001, "地址不能为空");
			}
			if (StringUtil.isBlank(trashtabEntity.getNo())) {
				return R.error(61002, "标签纸编号不能为空");
			}
			if (StringUtil.isBlank(trashtabEntity.getStreet())) {
				return R.error(61003, "路段名不能为空");
			}
			
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			BeanUtil.fillCCUUD(trashtabEntity, user.getId(), user.getId());
			trashtabService.save(trashtabEntity);
			
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "垃圾桶标记列表" , notes = "垃圾桶标记列表")
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public R list(@RequestBody TrashtabList trashtabList){
		TokenEntity token = tokenService.queryByToken(trashtabList.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			Map<String,Object> map = new HashMap<>();
			if (trashtabList.getPage() == null || trashtabList.getPage() < 1) {
				trashtabList.setPage(1);
			}
			if (trashtabList.getPagesize() == null || trashtabList.getPagesize() < 1) {
				trashtabList.setPagesize(10);
			}
			map.put("offset", (trashtabList.getPage()-1) * trashtabList.getPagesize());
			map.put("limit", trashtabList.getPagesize());
			List<TrashtabEntity> list = trashtabService.queryList(map);
			List<Map<String,Object>> data = new LinkedList<>();
			Map<String, Object> _map = null;
			for (TrashtabEntity tmp : list) {
				_map = new HashMap<>();
				_map.put("id", tmp.getId()); // Id
				_map.put("no", tmp.getNo()); // 标签纸编号
				_map.put("street", tmp.getStreet()); // 路段名
				_map.put("updtm", tmp.getUpdtm());   // 提交时间
				_map.put("person", sysUserService.queryObject(tmp.getUpdid()).getRealname()); // 处理人
				_map.put("addr", tmp.getAddr()); // 地址
				_map.put("lat", tmp.getLat()); // 纬度
				_map.put("lng", tmp.getLng()); // 经度
				_map.put("img", tmp.getImg()); // 图片
				data.add(_map);
			}
			Map<String,Object> ret = new HashMap<>();
			ret.put("page", trashtabList.getPage());    // 当前页
			ret.put("pagesize", trashtabList.getPagesize()); // 每页显示条数
			ret.put("count", trashtabService.queryTotal(null)); // 总条数
			ret.put("data", data);
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "垃圾桶标记详情" , notes = "垃圾桶标记详情")
	@RequestMapping(value = "/detail" , method = RequestMethod.POST)
	@ResponseBody
	public R detail(@RequestBody TrashtabDetailEntity trashtabDetail){
		TokenEntity token = tokenService.queryByToken(trashtabDetail.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			TrashtabEntity trashtab = trashtabService.queryObject(trashtabDetail.getId());
			Map<String, Object> _map = null;
			_map = new HashMap<>();
			_map.put("id", trashtab.getId()); // Id
			_map.put("no", trashtab.getNo()); // 标签纸编号
			_map.put("street", trashtab.getStreet()); // 路段名
			_map.put("updtm", trashtab.getUpdtm());   // 提交时间
			_map.put("person", sysUserService.queryObject(trashtab.getUpdid()).getRealname()); // 处理人
			_map.put("addr", trashtab.getAddr()); // 地址
			_map.put("lat", trashtab.getLat()); // 纬度
			_map.put("lng", trashtab.getLng()); // 经度
			_map.put("img", trashtab.getImg()); // 图片
			_map.put("rmk", trashtab.getRmk()); // 备注
			return R.ok().putData(_map);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
}
