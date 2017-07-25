package com.zhcs.utils;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

//*****************************************************************************
/**
* <p>Title:LogUtil</p>
* <p>Description:地图工具</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
* @author 刘晓东 - Alter
* @version v1.0 2017年2月23日
*/
//*****************************************************************************
public class MapUtil {
	
	//*************************************************************************
	/** 
	* 【判断】判断点是否在多边形内
	* @param point 检测点
	* @param pts  多边形的顶点
	* @return   点在多边形内返回true,否则返回false
	*/
	//*************************************************************************
	public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts){
	    int N = pts.size();
	    boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
	    int intersectCount = 0;//cross points count of x 
	    double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
	    Point2D.Double p1, p2; //Neighbour bound vertices 邻居约束顶点
	    Point2D.Double p = point; //当前点
	    
	    p1 = pts.get(0);//left vertex      左边顶点
	    for(int i = 1; i <= N; ++i){//check all rays     检查所有的光线      
	        if(p.equals(p1)){
	            return boundOrVertex;//p is an vertex p为顶点
	        }
	        
	        p2 = pts.get(i % N);//right vertex            
	        if(p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)){//ray is outside of our interests                
	            p1 = p2; 
	            continue;//next ray left point 接下来射线左点
	        }
	        
	        if(p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)){//ray is crossing over by the algorithm (common part of)
	            if(p.y <= Math.max(p1.y, p2.y)){//x is before of ray                    
	                if(p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)){//overlies on a horizontal ray
	                    return boundOrVertex;
	                }
	                
	                if(p1.y == p2.y){//ray is vertical                        
	                    if(p1.y == p.y){//overlies on a vertical ray
	                        return boundOrVertex;
	                    }else{//before ray
	                        ++intersectCount;
	                    } 
	                }else{//cross point on the left side                        
	                    double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y                        
	                    if(Math.abs(p.y - xinters) < precision){//overlies on a ray
	                        return boundOrVertex;
	                    }
	                    
	                    if(p.y < xinters){//before ray
	                        ++intersectCount;
	                    } 
	                }
	            }
	        }else{//special case when ray is crossing through the vertex                
	            if(p.x == p2.x && p.y <= p2.y){//p crossing over p2                    
	                Point2D.Double p3 = pts.get((i+1) % N); //next vertex                    
	                if(p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)){//p.x lies between p1.x & p3.x
	                    ++intersectCount;
	                }else{
	                    intersectCount += 2;
	                }
	            }
	        }            
	        p1 = p2;//next ray left point
	    }
	    
	    if(intersectCount % 2 == 0){//偶数在多边形外
	        return false;
	    } else { //奇数在多边形内
	        return true;
	    }
	    
	}
	
	public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {  
	       GeneralPath p = new java.awt.geom.GeneralPath(); 
	          Point2D.Double first = polygon.get(0); 
	          p.moveTo(first.x, first.y); 
	          polygon.remove(0); 
	          for (Point2D.Double d : polygon) {  
	             p.lineTo(d.x, d.y); 
	          }  
	          p.lineTo(first.x, first.y); 
	          p.closePath(); 
	          return p.contains(point); 
	     }
	
	// 测试一个点是否在多边形内
	public static void main(String[] args) {

		Point2D.Double point = new Point2D.Double(114.0599, 22.684424);

		List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
		pts.add(new Point2D.Double(114.060159, 22.684151));
		pts.add(new Point2D.Double(114.060159, 22.684151));
		pts.add(new Point2D.Double(114.059923, 22.684445));
		pts.add(new Point2D.Double(114.059437, 22.684711));
		pts.add(new Point2D.Double(114.058952, 22.684378));
		pts.add(new Point2D.Double(114.058845, 22.683711));
		pts.add(new Point2D.Double(114.058647, 22.683094));
		pts.add(new Point2D.Double(114.05858, 22.683028));
		pts.add(new Point2D.Double(114.058283, 22.683411));
		pts.add(new Point2D.Double(114.058, 22.683628));
		pts.add(new Point2D.Double(114.057951, 22.683724));
		pts.add(new Point2D.Double(114.057816, 22.683865));
		pts.add(new Point2D.Double(114.057618, 22.684111));
		pts.add(new Point2D.Double(114.057497, 22.684215));
		pts.add(new Point2D.Double(114.057331, 22.684428));
		pts.add(new Point2D.Double(114.057237, 22.684445));
		pts.add(new Point2D.Double(114.056859, 22.684716));
		pts.add(new Point2D.Double(114.056482, 22.685057));
		pts.add(new Point2D.Double(114.056379, 22.685045));
		pts.add(new Point2D.Double(114.056298, 22.685049));
		pts.add(new Point2D.Double(114.056244, 22.684962));
		pts.add(new Point2D.Double(114.056181, 22.684591));
		pts.add(new Point2D.Double(114.056199, 22.684291));
		pts.add(new Point2D.Double(114.055718, 22.68419));
		pts.add(new Point2D.Double(114.054349, 22.684232));
		pts.add(new Point2D.Double(114.052812, 22.684215));
		pts.add(new Point2D.Double(114.052812, 22.683182));
		pts.add(new Point2D.Double(114.051995, 22.683082));
		pts.add(new Point2D.Double(114.050486, 22.683032));
		pts.add(new Point2D.Double(114.049767, 22.682998));
		pts.add(new Point2D.Double(114.04895, 22.683015));
		pts.add(new Point2D.Double(114.04842, 22.683123));
		pts.add(new Point2D.Double(114.047926, 22.68299));
		pts.add(new Point2D.Double(114.047566, 22.683065));
		pts.add(new Point2D.Double(114.046192, 22.68374));
		pts.add(new Point2D.Double(114.045365, 22.683674));
		pts.add(new Point2D.Double(114.044853, 22.683774));
		pts.add(new Point2D.Double(114.044521, 22.68389));
		pts.add(new Point2D.Double(114.044036, 22.682965));
		pts.add(new Point2D.Double(114.043488, 22.682123));
		pts.add(new Point2D.Double(114.043183, 22.681464));
		pts.add(new Point2D.Double(114.042967, 22.680823));
		pts.add(new Point2D.Double(114.042868, 22.680164));
		pts.add(new Point2D.Double(114.041871, 22.679922));
		pts.add(new Point2D.Double(114.040865, 22.679747));
		pts.add(new Point2D.Double(114.038888, 22.679616));
		pts.add(new Point2D.Double(114.0387, 22.680156));
		pts.add(new Point2D.Double(114.037766, 22.680931));
		pts.add(new Point2D.Double(114.037218, 22.681398));
		pts.add(new Point2D.Double(114.035834, 22.682156));
		pts.add(new Point2D.Double(114.034613, 22.682907));
		pts.add(new Point2D.Double(114.033409, 22.684891));
		pts.add(new Point2D.Double(114.032762, 22.686291));
		pts.add(new Point2D.Double(114.030777, 22.68665));
		pts.add(new Point2D.Double(114.028639, 22.686708));
		pts.add(new Point2D.Double(114.027929, 22.6873));
		pts.add(new Point2D.Double(114.026555, 22.687308));
		pts.add(new Point2D.Double(114.026366, 22.6869));
		pts.add(new Point2D.Double(114.025423, 22.686083));
		pts.add(new Point2D.Double(114.025351, 22.685783));
		pts.add(new Point2D.Double(114.02536, 22.685499));
		pts.add(new Point2D.Double(114.025521, 22.685218));
		pts.add(new Point2D.Double(114.02616, 22.684924));
		pts.add(new Point2D.Double(114.026977, 22.684732));
		pts.add(new Point2D.Double(114.027022, 22.68414));
		pts.add(new Point2D.Double(114.027283, 22.683032));
		pts.add(new Point2D.Double(114.027184, 22.682765));
		pts.add(new Point2D.Double(114.027166, 22.68244));
		pts.add(new Point2D.Double(114.027283, 22.682323));
		pts.add(new Point2D.Double(114.028163, 22.682323));
		pts.add(new Point2D.Double(114.028459, 22.682406));
		pts.add(new Point2D.Double(114.028648, 22.682348));
		pts.add(new Point2D.Double(114.028774, 22.682148));
		pts.add(new Point2D.Double(114.028603, 22.681615));
		pts.add(new Point2D.Double(114.028531, 22.681064));
		pts.add(new Point2D.Double(114.028037, 22.680681));
		pts.add(new Point2D.Double(114.027426, 22.680322));
		pts.add(new Point2D.Double(114.02704, 22.680547));
		pts.add(new Point2D.Double(114.026573, 22.680714));
		pts.add(new Point2D.Double(114.026205, 22.680839));
		pts.add(new Point2D.Double(114.024947, 22.680848));
		pts.add(new Point2D.Double(114.022755, 22.680748));
		pts.add(new Point2D.Double(114.021551, 22.680489));
		pts.add(new Point2D.Double(114.01918, 22.679872));
		pts.add(new Point2D.Double(114.015609, 22.678772));
		pts.add(new Point2D.Double(114.013404, 22.677846));
		pts.add(new Point2D.Double(114.012308, 22.677046));
		pts.add(new Point2D.Double(114.010511, 22.676413));
		pts.add(new Point2D.Double(114.010511, 22.676112));
		pts.add(new Point2D.Double(114.01021, 22.675279));
		pts.add(new Point2D.Double(114.008836, 22.673361));
		pts.add(new Point2D.Double(114.008872, 22.672994));
		pts.add(new Point2D.Double(114.00951, 22.672286));
		pts.add(new Point2D.Double(114.011068, 22.670843));
		pts.add(new Point2D.Double(114.012523, 22.669526));
		pts.add(new Point2D.Double(114.012793, 22.669259));
		pts.add(new Point2D.Double(114.013, 22.668843));
		pts.add(new Point2D.Double(114.013646, 22.667275));
		pts.add(new Point2D.Double(114.013853, 22.666358));
		pts.add(new Point2D.Double(114.014625, 22.664557));
		pts.add(new Point2D.Double(114.015605, 22.664478));
		pts.add(new Point2D.Double(114.019139, 22.664386));
		pts.add(new Point2D.Double(114.019422, 22.664149));
		pts.add(new Point2D.Double(114.019319, 22.66261));
		pts.add(new Point2D.Double(114.019499, 22.662394));
		pts.add(new Point2D.Double(114.020235, 22.66226));
		pts.add(new Point2D.Double(114.02117, 22.66191));
		pts.add(new Point2D.Double(114.021762, 22.661476));
		pts.add(new Point2D.Double(114.02399, 22.657975));
		pts.add(new Point2D.Double(114.02417, 22.657541));
		pts.add(new Point2D.Double(114.024565, 22.65539));
		pts.add(new Point2D.Double(114.024907, 22.654789));
		pts.add(new Point2D.Double(114.027925, 22.651062));
		pts.add(new Point2D.Double(114.028455, 22.650537));
		pts.add(new Point2D.Double(114.029209, 22.650078));
		pts.add(new Point2D.Double(114.030543, 22.649586));
		pts.add(new Point2D.Double(114.031033, 22.649486));
		pts.add(new Point2D.Double(114.0315, 22.64947));
		pts.add(new Point2D.Double(114.033261, 22.649486));
		pts.add(new Point2D.Double(114.034873, 22.649511));
		pts.add(new Point2D.Double(114.036023, 22.649345));
		pts.add(new Point2D.Double(114.039149, 22.648077));
		pts.add(new Point2D.Double(114.041143, 22.647285));
		pts.add(new Point2D.Double(114.042132, 22.646939));
		pts.add(new Point2D.Double(114.045015, 22.646038));
		pts.add(new Point2D.Double(114.049758, 22.644683));
		pts.add(new Point2D.Double(114.049668, 22.644308));
		pts.add(new Point2D.Double(114.049462, 22.643716));
		pts.add(new Point2D.Double(114.048698, 22.642073));
		pts.add(new Point2D.Double(114.047791, 22.642048));
		pts.add(new Point2D.Double(114.047077, 22.641957));
		pts.add(new Point2D.Double(114.046713, 22.641765));
		pts.add(new Point2D.Double(114.04731, 22.641023));
		pts.add(new Point2D.Double(114.047544, 22.640606));
		pts.add(new Point2D.Double(114.047724, 22.640372));
		pts.add(new Point2D.Double(114.04714, 22.639605));
		pts.add(new Point2D.Double(114.047122, 22.639117));
		pts.add(new Point2D.Double(114.047158, 22.638938));
		pts.add(new Point2D.Double(114.047274, 22.638842));
		pts.add(new Point2D.Double(114.047849, 22.638454));
		pts.add(new Point2D.Double(114.047535, 22.638229));
		pts.add(new Point2D.Double(114.047238, 22.637078));
		pts.add(new Point2D.Double(114.047391, 22.636553));
		pts.add(new Point2D.Double(114.050984, 22.636128));
		pts.add(new Point2D.Double(114.054618, 22.635644));
		pts.add(new Point2D.Double(114.056653, 22.635235));
		pts.add(new Point2D.Double(114.058207, 22.634827));
		pts.add(new Point2D.Double(114.058669, 22.634597));
		pts.add(new Point2D.Double(114.058966, 22.634631));
		pts.add(new Point2D.Double(114.059375, 22.634856));
		pts.add(new Point2D.Double(114.059797, 22.63531));
		pts.add(new Point2D.Double(114.059869, 22.635602));
		pts.add(new Point2D.Double(114.060349, 22.63604));
		pts.add(new Point2D.Double(114.060758, 22.636411));
		pts.add(new Point2D.Double(114.060596, 22.636736));
		pts.add(new Point2D.Double(114.060538, 22.637057));
		pts.add(new Point2D.Double(114.060515, 22.637462));
		pts.add(new Point2D.Double(114.060493, 22.638062));
		pts.add(new Point2D.Double(114.060457, 22.638413));
		pts.add(new Point2D.Double(114.058827, 22.638429));
		pts.add(new Point2D.Double(114.058526, 22.639359));
		pts.add(new Point2D.Double(114.058458, 22.640151));
		pts.add(new Point2D.Double(114.058606, 22.641194));
		pts.add(new Point2D.Double(114.062784, 22.640426));
		pts.add(new Point2D.Double(114.063439, 22.641294));
		pts.add(new Point2D.Double(114.064104, 22.641961));
		pts.add(new Point2D.Double(114.063197, 22.642553));
		pts.add(new Point2D.Double(114.063969, 22.643249));
		pts.add(new Point2D.Double(114.063067, 22.644058));
		pts.add(new Point2D.Double(114.062824, 22.644266));
		pts.add(new Point2D.Double(114.06211, 22.643624));
		pts.add(new Point2D.Double(114.061566, 22.644179));
		pts.add(new Point2D.Double(114.061427, 22.644533));
		pts.add(new Point2D.Double(114.061638, 22.6449));
		pts.add(new Point2D.Double(114.061697, 22.645596));
		pts.add(new Point2D.Double(114.061638, 22.645801));
		pts.add(new Point2D.Double(114.061256, 22.646247));
		pts.add(new Point2D.Double(114.060857, 22.646551));
		pts.add(new Point2D.Double(114.061113, 22.647177));
		pts.add(new Point2D.Double(114.062258, 22.647556));
		pts.add(new Point2D.Double(114.062402, 22.647664));
		pts.add(new Point2D.Double(114.06242, 22.647998));
		pts.add(new Point2D.Double(114.062402, 22.648907));
		pts.add(new Point2D.Double(114.062604, 22.649111));
		pts.add(new Point2D.Double(114.062757, 22.649586));
		pts.add(new Point2D.Double(114.063174, 22.649691));
		pts.add(new Point2D.Double(114.06335, 22.650008));
		pts.add(new Point2D.Double(114.063107, 22.650379));
		pts.add(new Point2D.Double(114.063237, 22.650862));
		pts.add(new Point2D.Double(114.063201, 22.651225));
		pts.add(new Point2D.Double(114.062496, 22.651296));
		pts.add(new Point2D.Double(114.061023, 22.652355));
		pts.add(new Point2D.Double(114.061495, 22.653209));
		pts.add(new Point2D.Double(114.061045, 22.653059));
		pts.add(new Point2D.Double(114.060628, 22.652897));
		pts.add(new Point2D.Double(114.060277, 22.653309));
		pts.add(new Point2D.Double(114.059038, 22.653931));
		pts.add(new Point2D.Double(114.05835, 22.654106));
		pts.add(new Point2D.Double(114.057973, 22.654339));
		pts.add(new Point2D.Double(114.057663, 22.653622));
		pts.add(new Point2D.Double(114.056981, 22.65263));
		pts.add(new Point2D.Double(114.056446, 22.652793));
		pts.add(new Point2D.Double(114.056823, 22.653685));
		pts.add(new Point2D.Double(114.055858, 22.653993));
		pts.add(new Point2D.Double(114.055211, 22.654443));
		pts.add(new Point2D.Double(114.055772, 22.655398));
		pts.add(new Point2D.Double(114.057502, 22.654648));
		pts.add(new Point2D.Double(114.0576, 22.655398));
		pts.add(new Point2D.Double(114.057924, 22.656032));
		pts.add(new Point2D.Double(114.058094, 22.656278));
		pts.add(new Point2D.Double(114.05884, 22.655932));
		pts.add(new Point2D.Double(114.059101, 22.655848));
		pts.add(new Point2D.Double(114.059801, 22.655782));
		pts.add(new Point2D.Double(114.06052, 22.655677));
		pts.add(new Point2D.Double(114.060641, 22.655619));
		pts.add(new Point2D.Double(114.061297, 22.655853));
		pts.add(new Point2D.Double(114.060902, 22.666262));
		pts.add(new Point2D.Double(114.061652, 22.666371));
		pts.add(new Point2D.Double(114.062249, 22.666037));
		pts.add(new Point2D.Double(114.062689, 22.666058));
		pts.add(new Point2D.Double(114.063062, 22.66605));
		pts.add(new Point2D.Double(114.063453, 22.666508));
		pts.add(new Point2D.Double(114.063664, 22.666929));
		pts.add(new Point2D.Double(114.063641, 22.667146));
		pts.add(new Point2D.Double(114.06352, 22.667292));
		pts.add(new Point2D.Double(114.063246, 22.667521));
		pts.add(new Point2D.Double(114.063561, 22.668084));
		pts.add(new Point2D.Double(114.063718, 22.668372));
		pts.add(new Point2D.Double(114.06282, 22.668818));
		pts.add(new Point2D.Double(114.062038, 22.669239));
		pts.add(new Point2D.Double(114.060875, 22.669314));
		pts.add(new Point2D.Double(114.060372, 22.671214));
		pts.add(new Point2D.Double(114.060444, 22.671981));
		pts.add(new Point2D.Double(114.06127, 22.671548));
		pts.add(new Point2D.Double(114.063462, 22.670714));
		pts.add(new Point2D.Double(114.063103, 22.671648));
		pts.add(new Point2D.Double(114.065007, 22.671248));
		pts.add(new Point2D.Double(114.066291, 22.670664));
		pts.add(new Point2D.Double(114.067702, 22.672482));
		pts.add(new Point2D.Double(114.068924, 22.674449));
		pts.add(new Point2D.Double(114.068492, 22.676183));
		pts.add(new Point2D.Double(114.068528, 22.677851));
		pts.add(new Point2D.Double(114.069705, 22.679568));
		pts.add(new Point2D.Double(114.070028, 22.680168));
		pts.add(new Point2D.Double(114.070639, 22.680347));
		pts.add(new Point2D.Double(114.071133, 22.680297));
		pts.add(new Point2D.Double(114.070774, 22.679647));
		pts.add(new Point2D.Double(114.071214, 22.679355));
		pts.add(new Point2D.Double(114.070864, 22.678905));
		pts.add(new Point2D.Double(114.070828, 22.678422));
		pts.add(new Point2D.Double(114.072193, 22.677438));
		pts.add(new Point2D.Double(114.072355, 22.67763));
		pts.add(new Point2D.Double(114.072939, 22.677538));
		pts.add(new Point2D.Double(114.073379, 22.678013));
		pts.add(new Point2D.Double(114.073756, 22.678563));
		pts.add(new Point2D.Double(114.072957, 22.679114));
		pts.add(new Point2D.Double(114.073253, 22.679397));
		pts.add(new Point2D.Double(114.073568, 22.680014));
		pts.add(new Point2D.Double(114.073217, 22.680189));
		pts.add(new Point2D.Double(114.072885, 22.680131));
		pts.add(new Point2D.Double(114.072526, 22.680364));
		pts.add(new Point2D.Double(114.072077, 22.681123));
		pts.add(new Point2D.Double(114.071466, 22.68194));
		pts.add(new Point2D.Double(114.071511, 22.682236));
		pts.add(new Point2D.Double(114.071026, 22.682627));
		pts.add(new Point2D.Double(114.07099, 22.682953));
		pts.add(new Point2D.Double(114.070756, 22.683836));
		pts.add(new Point2D.Double(114.070572, 22.684728));
		pts.add(new Point2D.Double(114.0701, 22.68547));
		pts.add(new Point2D.Double(114.069229, 22.685212));
		pts.add(new Point2D.Double(114.068699, 22.684736));
		pts.add(new Point2D.Double(114.0686, 22.684028));
		pts.add(new Point2D.Double(114.068879, 22.683403));
		pts.add(new Point2D.Double(114.068403, 22.683078));
		pts.add(new Point2D.Double(114.06816, 22.682702));
		pts.add(new Point2D.Double(114.065672, 22.682794));
		pts.add(new Point2D.Double(114.064615, 22.68215));
		pts.add(new Point2D.Double(114.064306, 22.682561));
		pts.add(new Point2D.Double(114.063713, 22.682761));
		pts.add(new Point2D.Double(114.063803, 22.683211));
		pts.add(new Point2D.Double(114.063641, 22.683628));
		pts.add(new Point2D.Double(114.062891, 22.684353));
		pts.add(new Point2D.Double(114.062028, 22.684951));
		pts.add(new Point2D.Double(114.060839, 22.684645));
		pts.add(new Point2D.Double(114.060159, 22.684151));

		if (IsPtInPoly(point, pts)) {
			System.out.println("点在多边形内");
		} else {
			System.out.println("点在多边形外");
		}
	}
}
