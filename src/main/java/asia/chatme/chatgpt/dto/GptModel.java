package asia.chatme.chatgpt.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * {
 *   "choices" : [
 *     {
 *       "finish_reason" : "stop",
 *       "index" : 0,
 *       "logprobs" : null,
 *       "text" : "\n\n张三穿越到岳飞的家乡，被誉为岳飞家族的宝贵继承者——岳云。随着金兵不断压向江左，岳家军队只是英勇抵抗，但力量却大不如前，朝廷授意令岳家立下赌注，赌哪个军队能够抵抗金兵，赢得朝廷的赏赐。\n\n张三为了避免岳家军的失败而迅速做出行动，他先说服四周的小县长，让他们帮助岳家军，然后找到最优秀的士兵，投资军火，加强军队装备装备。此外，还鼓舞士气、调整部队阵型，为岳家军增添了一群利器。\n\n随着每一次战斗，岳家军的士气越来越高，士兵的斗志也将他们引向胜利的旗帜。最终，岳飞的子孙坚持不懈，最终击败金兵，取得了荣誉的胜利，岳家征服朝廷拨款，实现了穿越之后的岳家的辉煌！"
 *     }
 *   ],
 *   "created" : 1673449503,
 *   "id" : "cmpl-6XWwxcNY2nJeLgw3vH0YXSNfNPcMF",
 *   "model" : "text-davinci-003",
 *   "object" : "text_completion",
 *   "usage" : {
 *     "completion_tokens" : 604,
 *     "prompt_tokens" : 103,
 *     "total_tokens" : 707
 *   }
 * }
 */
@Data
public class GptModel implements Serializable {

    private static final long serialVersionUID = 8151473180372096155L;

    @Data
    public static class Usage implements Serializable{
        private static final long serialVersionUID = -8382699015077663641L;
        private Integer completionTokens;
        private Integer promptTokens;
        private Integer totalTokens;
    }

    @Data
    public static class Choice implements Serializable{
        private static final long serialVersionUID = 5950922574824510052L;
        private String finishReason;
        private Integer index;
        private String text;

    }
    private String id;
    private Long created;
    private String model;
    private String object;
    private Usage usage;
    private List<Choice> choices;

    public static void main(String[] args) {
        String json = "{\"id\":\"cmpl-6Xo0whBgMkSnSnjaGRzoSiSnnVOL2\",\"object\":\"text_completion\",\"created\":1673515098,\"model\":\"text-davinci-003\",\"choices\":[{\"text\":\"\\n\\nHello!\",\"index\":0,\"logprobs\":null,\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":2,\"completion_tokens\":4,\"total_tokens\":6}}";
        GptModel model1 = JSONObject.parseObject(json, GptModel.class);
        System.out.println(JSON.toJSONString(model1));
    }
}
