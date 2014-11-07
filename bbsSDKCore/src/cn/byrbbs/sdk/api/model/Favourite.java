/*
 * Copyright (C) 2010-2014 dss886
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.byrbbs.sdk.api.model;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * é?æ°æ£æ¾¶å­ç²¨éåªç¶
 * @author dss886
 * @since 2014-9-7
 */
public class Favourite {

	/** 
	 * é?æ°æ£æ¾¶å­éªéå¸®ç´æ¤¤è·ºç°éæ°æ£æ¾¶ç­¶evelæ¶ï¿½0
	 * å§ãçé¬Ñè´éå©æé¹î¼çé¬ï¿?
	 *  */
	public int level;
	/** 
	 * é?æ°æ£æ¾¶å­æ´°è¤°ï¿?
	 * å§ãçé¬Ñè´éå©æé¹î¼çé¬ï¿?
	 *  */
	public String description;
	/** 
	 * é?æ°æ£æ¾¶å­æ´°è¤°æç¶ç¼îç´çã¥ï¿½è©æ¤æµåº¡å¹éãæ¹éå¿ãé©î¼ç¶
	 * å§ãçé¬Ñè´éå©æé¹î¼çé¬ï¿?
	 * 	 */
	public int position;
	/** çã¥ç°éæ°æ£æ¾¶ç°å¯éî¤æ®é·îç¾æ¶å¤æ´°è¤°æ æ®éæ®ç²éå±¾æç¼å«åç»±ç±è´éæ°æ£æ¾¶ç°åéçåµ */
	public List<Favourite> sub_favorite = new ArrayList<Favourite>();
	/** çã¥ç°éæ°æ£æ¾¶ç°å¯éî¤æ®éåå°¯é¨å¬æç¼å¶ç´éæ®ç²éåªç¤æ¶ååéååéçåµ */
	public List<Section> sections = new ArrayList<Section>();
	/** çã¥ç°éæ°æ£æ¾¶ç°å¯éî¤æ®éå ¥æ½°é¨å¬æç¼å¶ç´éæ®ç²éåªç¤æ¶è¹å¢éã åéçåµ */
	public List<Board> boards = new ArrayList<Board>();
	
	public static Favourite parse(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return Favourite.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	public static Favourite parse(JSONObject jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        Favourite favourite = new Favourite();
        favourite.level = jsonObject.optInt("level", -1);
        favourite.description = jsonObject.optString("description", "");
        favourite.position = jsonObject.optInt("position", -1);
        JSONArray jsonSubFavourite = jsonObject.optJSONArray("sub_favorite");
        for(int i = 0; i < jsonSubFavourite.length(); i++){
        	favourite.sub_favorite.add(Favourite.parse(jsonSubFavourite.optJSONObject(i)));
		}
        JSONArray jsonSections = jsonObject.optJSONArray("section");
        for(int i = 0; i < jsonSections.length(); i++){
        	favourite.sections.add(Section.parse(jsonSections.optJSONObject(i)));
		}
        JSONArray jsonBoards = jsonObject.optJSONArray("board");
        for(int i = 0; i < jsonBoards.length(); i++){
        	favourite.boards.add(Board.parse(jsonBoards.optJSONObject(i)));
		}
        return favourite;
	}
}
