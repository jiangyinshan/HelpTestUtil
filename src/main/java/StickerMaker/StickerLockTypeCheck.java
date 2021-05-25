package StickerMaker;

import StickerMaker.NewBean.NewStickerRoot;
import StickerMaker.NewBean.ResourceList;
import StickerMaker.OldBean.OldStickerRoot;
import StickerMaker.OldBean.Resource_list;
import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 检查firebase配置的stickerPack资源和接口下发的stickerPack资源是否一致
 **/
public class StickerLockTypeCheck {
    public final static String OldStickerPageJson = "{\n" +
            "    \"errorCode\":0,\n" +
            "    \"errorMsg\":\"ok\",\n" +
            "    \"data\":{\n" +
            "        \"page_name\":\"HOT\",\n" +
            "        \"key\":\"c429fde8b1b986d42f84ba63dbfef6ac\",\n" +
            "        \"resource_list\":[\n" +
            "            {\n" +
            "                \"key\":\"45e8034fda091cc28fc69d707fb694cf\",\n" +
            "                \"name\":\"Cool Swag Emoji\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cool.swag.emoji\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/cool_swag_emoji_2.json\",\n" +
            "                \"lock_type\":0,\n" +
            "                \"priority\":82\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"dd7e21f062bda643cdfa89d3c9ba0e66\",\n" +
            "                \"name\":\"Funny Faces\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funny.faces\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/funny_faces_2.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":81\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"f1748c947efbc92a56f75986c3650052\",\n" +
            "                \"name\":\"Mister Weed\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.mister.weed\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Mister_Weed.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":80\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"59603c435184dd4834a510ede457689c\",\n" +
            "                \"name\":\"Neon Words\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.neon.words\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Neon_Words.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":79\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"69e3ff6715022dc23ba017eca52c7c69\",\n" +
            "                \"name\":\"Emoji Party\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.party\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/emoji_party.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":78\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"c58a83659f344c4e8af14e836f9b2911\",\n" +
            "                \"name\":\"New Funky\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.new.funky\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/new_funky.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":77\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ef4f947bfc69de59bbce0d921a555ad3\",\n" +
            "                \"name\":\"Emoji Pixel\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.pixel\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Emoji_Pixel.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":76\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"5d53d397543847d97fe3c7837d1ce6c5\",\n" +
            "                \"name\":\"Positive Vibes\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.positive.vibes\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Positive_Vibes.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":75\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"3fffaca26b73b632952bdca373bfba2a\",\n" +
            "                \"name\":\"Emoji Teeth\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.teeth\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Emoji_Teeth.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":74\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"55f25812bb19b1cd94ea6a818589f9cb\",\n" +
            "                \"name\":\"Glitter Sticker\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.glitter_emoji\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/glitter_sticker.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":73\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"93cc2738a4bf4ea9a26e2249f1b3574b\",\n" +
            "                \"name\":\"Black Kitty\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.black.kitty\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Black_Kitty.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":72\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"7b84dccf84a39aeccb1c446513c1d3f3\",\n" +
            "                \"name\":\"God Is Love\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.god.is.love\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/God_Is_Love.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":71\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"d161e8f5b949140b3aca98c2c626b692\",\n" +
            "                \"name\":\"Emoji Ugly\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.ugly\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Emoji_Ugly.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":70\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"b23bda846a5139edd3d0d4f46952e89b\",\n" +
            "                \"name\":\"Cute Panda\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cutepanda\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/cute_panda.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":69\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"5875f1a633cd37b70481c203361569c7\",\n" +
            "                \"name\":\"Donut And Coffee\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.donut.and.coffee\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Donut_And_Coffee.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":68\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"1992b50f03d998f9f2864877afb546c2\",\n" +
            "                \"name\":\"Super Llama\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.super.llama\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Super_Llama.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":67\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ae7e9e45fad1a3eca141d364f0390bae\",\n" +
            "                \"name\":\"Eggcellent\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.eggcellent\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Eggcellent.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":66\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"9fe3c9377784854c7af1ccf6809149f4\",\n" +
            "                \"name\":\"Funny Animal\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funnyanimal\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/funny_animal.json\",\n" +
            "                \"lock_type\":4,\n" +
            "                \"priority\":65\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"5c0a301510b750cf83248345e36dfffd\",\n" +
            "                \"name\":\"Valentine Love\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.valentine.love\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Valentine_Love.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":64\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"0d7dc308d48e09523855bfd206c27243\",\n" +
            "                \"name\":\"Scary Halloween\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.scary.halloween\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Scary_Halloween_2.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":63\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ce36348ecfd8396a6358b62f92b8c015\",\n" +
            "                \"name\":\"Easter Delight\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.easter.delight\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Easter_Delight.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":62\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"531aa0af038651801b9e31b87b68398f\",\n" +
            "                \"name\":\"Emoji Pumpkin\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.pumpkin\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Emoji_Pumpkin.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":61\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"8f557a3d443cacf0b9b923b003f48fa1\",\n" +
            "                \"name\":\"Funny Cactus\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funny.cactus\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Funny_Cactus.json\",\n" +
            "                \"lock_type\":0,\n" +
            "                \"priority\":60\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"dfae91a2f695ee17983a50b788bc0ddc\",\n" +
            "                \"name\":\"Fluffy Polar Bear\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.fluffy.polar.bear\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Fluffy_Polar_Bear.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":59\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"d12b3619aaf99b8727b234f76e2350d8\",\n" +
            "                \"name\":\"Braids Cute Girl\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.braids.cute.girl\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Braids_Cute_Girl.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":58\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"b54c07f02f1a7bcafa747ad707fbc5eb\",\n" +
            "                \"name\":\"Goofy Plants\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.goofy.plants\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Goofy_Plants.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":57\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"68ca9edfe6dcd902929445eb7158a315\",\n" +
            "                \"name\":\"Bun Man Couple\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.bun.man.couple\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Bun_Man_Couple.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":56\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"67ed9d6439af5d2fa269278d6e34b629\",\n" +
            "                \"name\":\"Happy Sloth\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.happy.sloth\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Happy_Sloth.json\",\n" +
            "                \"lock_type\":3,\n" +
            "                \"priority\":55\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"a50b192e7d794ede605b113c518b9e92\",\n" +
            "                \"name\":\"Ghost Office Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.ghost.office.life\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/ghost_office_life.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":54\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"3b50a090015d2f32a2692bb75de035e4\",\n" +
            "                \"name\":\"Christmas Snowman\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.christmas.snowman\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Christmas_Snowman.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":53\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"69d9131009510575823c12e623a90569\",\n" +
            "                \"name\":\"Gen Z Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.gen.z.life\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Gen_Z_Life.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":52\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"950147e7d431d0a70c6180b8b8fd78ef\",\n" +
            "                \"name\":\"Gingerbread Cookie\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.gingerbread.cookie\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Gingerbread_Cookie.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":51\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"4070a4b2e30f1867c8c740d8f790e1f9\",\n" +
            "                \"name\":\"Little Parakeet\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.little.parakeet\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Little_Parakeet.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":50\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"db24f61dc88f4b7de923a8b56b92e323\",\n" +
            "                \"name\":\"Chubby Penguin\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.chubby.penguin\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Chubby_Penguin.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":49\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"e767957e8561f287cfc6874cd0bdee58\",\n" +
            "                \"name\":\"Spooky Gang\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.spooky.gang\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Spooky_Gang.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":48\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"26ea89596d256ad78c673590d3e74600\",\n" +
            "                \"name\":\"Emoji Lovely\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emoji.lovely\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Emoji_Lovely.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":47\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"6694414670a33ebec72704bb27d11780\",\n" +
            "                \"name\":\"Pastel Love Words\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.pastel.love.words\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Pastel_Love_Words.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":46\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"d900a32603872569fd0e7ac4493f2bf8\",\n" +
            "                \"name\":\"Silly Marshmallow\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.silly.marshmallow\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Silly_Marshmallow.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":45\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ed428c3629be802343ba67161880de52\",\n" +
            "                \"name\":\"Fun Date Night\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.fun.date.night\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Fun_Date_Night.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":44\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"dd7010b122159875df5916ce3e71ef7e\",\n" +
            "                \"name\":\"Turtle Baby\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.turtle.baby\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Turtle_Baby.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":43\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"9570866f3e6756c75caa0501d2474041\",\n" +
            "                \"name\":\"Llama Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.llama.life\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Llama_Life.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":42\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"00105c6756db7a37dcae34f6e5b74949\",\n" +
            "                \"name\":\"I Love You Forever\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.i.love.you.forever\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/i_love_you_forever.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":41\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"2586846ed81b9c53c61c14c0243ce4c2\",\n" +
            "                \"name\":\"Emoji Love\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.emojilove\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/emoji_love.json\",\n" +
            "                \"lock_type\":3,\n" +
            "                \"priority\":40\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"0450db55c572258efdefe41daa035ba2\",\n" +
            "                \"name\":\"Cute Reaper\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cute.reaper\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cute_Reaper.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":39\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"4313945153159ebb46463e2736f34a14\",\n" +
            "                \"name\":\"Bunny In Love\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.bunny.in.love\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Bunny_In_Love.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":38\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"e05af2e078403b015845217d24dbebca\",\n" +
            "                \"name\":\"Wacky Cupcake\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.wacky.cupcake\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Wacky_Cupcake.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":37\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"01b8a0b866b0a355d11281fc7a513270\",\n" +
            "                \"name\":\"Yummy Words\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.yummy.words\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Yummy_Words.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":36\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"b65ecb42be9fcca2173cf7d1afc65c95\",\n" +
            "                \"name\":\"Avocado Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.avocadolife\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/avocado_life.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":35\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"cda4c56e8a277216129ba307ebe978a2\",\n" +
            "                \"name\":\"Cool Gang\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cool.gang\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cool_Gang.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":34\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ab939d23656f06eabdbfda36493529a3\",\n" +
            "                \"name\":\"Cool Grandpa\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cool.grandpa\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cool_Grandpa.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":33\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"a68d1aca5e40105f1939a79c81a910d9\",\n" +
            "                \"name\":\"Work From Home\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.work.from.home\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Work_From_Home.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":32\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"12ff7f107ce552c42808b6f160a06923\",\n" +
            "                \"name\":\"Happy Jolly Christmas\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.happy.jolly.christmas\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Happy_Jolly_Christmas.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":31\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"35eec8e5ce092f5666f773f37f491a40\",\n" +
            "                \"name\":\"Weird Aliens\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.weird.aliens\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Weird_Aliens.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":30\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"e26ea351e86615b43d4556a4cb1976af80acd806\",\n" +
            "                \"name\":\"Stick Figure Words\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.stick.figure.words\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Stick_Figure_Words.json\",\n" +
            "                \"lock_type\":4,\n" +
            "                \"priority\":29\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"3a61a21fc10219e6dd607ca5a83a2a06\",\n" +
            "                \"name\":\"Lovely Avocado\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.lovely.avocado\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Lovely_Avocado.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":28\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"c528215918907a6ff5b25c30fcd08000\",\n" +
            "                \"name\":\"Fun Chili\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.fun.chili\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Fun_Chili.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":27\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"267049b08a02f9ad7fb3dea091d26f8b\",\n" +
            "                \"name\":\"Bread Love\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.bread.love\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Bread_Love.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":26\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"326edd09637363a67fc1ec2fd1bca1ec\",\n" +
            "                \"name\":\"Happy Earth\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.happy.earth\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Happy_Earth.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":25\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"db01977658bbb4d28640d425c0554950\",\n" +
            "                \"name\":\"Funny Bun Man\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funny.bun.man\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Funny_Bun_Man.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":24\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"3cae8848e3c52e563a3b2eeb08acd099\",\n" +
            "                \"name\":\"Weird Cats\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.weird.cats\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Weird_Cats.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":23\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"b5d9076aaabb7c3e1c8e1e44d77dd95b\",\n" +
            "                \"name\":\"Fluff Ball\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.fluff.ball\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Fluff_Ball.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":22\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"88a817a5455c1370dc6b39032f2e757b\",\n" +
            "                \"name\":\"Cute Baby Girl\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cute.baby.girl\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cute_Baby_Girl.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":21\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"5bb5b01394e4f0f0cd17cabff8b707b6\",\n" +
            "                \"name\":\"Cinco De Mayo\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cinco.de.mayo\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cinco_De_Mayo.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":20\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"fbfe7e81ba3fea3ad6ab9f71f51f9196\",\n" +
            "                \"name\":\"Bubble Tea\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.bubble.tea\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Bubble_Tea.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":19\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"6eaa4b6c5083231d99da0dd013b04778\",\n" +
            "                \"name\":\"Baby Hedgehog\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.baby.hedgehog\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Baby_Hedgehog.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":18\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"cb3749054cdfe0806e220b1076ae5dab\",\n" +
            "                \"name\":\"Happy Birthday To You\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.happy.birthday.to.you\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/happy_birthday_to_you.json\",\n" +
            "                \"lock_type\":3,\n" +
            "                \"priority\":17\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"a946ca8e2dfb45a3a0dd0c6371e7a18e\",\n" +
            "                \"name\":\"Funny Dino\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funny.dino\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Funny_Dino.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":16\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"1efa75ae9db9d91abc8741c58498c1bd\",\n" +
            "                \"name\":\"Lovely Grandma\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.lovely.grandma\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Lovely_Grandma.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":15\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"e56de71e0d24d5874e195832d9ed5718\",\n" +
            "                \"name\":\"Yummy Sushi\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.yummy_sushi\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/yummy_sushi.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":14\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"57094ee3a2521ce0bda68068bfe19f98\",\n" +
            "                \"name\":\"Broken Animals\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.broken.animals\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Broken_Animals.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":13\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"3e34897866f75c5a61889f95ce14ca57\",\n" +
            "                \"name\":\"Bun Man Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.bun.man.life\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Bun_Man_Life.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":12\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"92d8e94efa8eb7712372dfaba3d2c3d2\",\n" +
            "                \"name\":\"Flirt With You\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.flirt.with.you\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Flirt_With_You.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":11\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"74d2ff9e0c0385cd4678443f04b42f6b\",\n" +
            "                \"name\":\"Silly Corgi\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.silly.corgi\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Silly_Corgi.json\",\n" +
            "                \"lock_type\":4,\n" +
            "                \"priority\":10\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"fe062264395eda1082f65ff800856bea\",\n" +
            "                \"name\":\"Fat Lazy Cats\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.fat.lazy.cats\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Fat_Lazy_Cats.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":9\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"2c90aaf7b12374b698ac3a0f7c684045\",\n" +
            "                \"name\":\"Diwali\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.diwali\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/diwali.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":8\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"57354b72e164ad8687194d22295823eb\",\n" +
            "                \"name\":\"Celebration Time\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.celebration.time\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Celebration_Time.json\",\n" +
            "                \"lock_type\":2,\n" +
            "                \"priority\":7\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"382a83ec0a8b1d4d4b952f3c1450c9ff\",\n" +
            "                \"name\":\"Rainbow Unicorn\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.rainbow_unicorn\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/rainbow_unicorn.json\",\n" +
            "                \"lock_type\":4,\n" +
            "                \"priority\":6\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"7c6cb36a4b22c77f50179bcab081ed62\",\n" +
            "                \"name\":\"Kitty Cat Life\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.kitty.cat.life\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Kitty_Cat_Life.json\",\n" +
            "                \"lock_type\":3,\n" +
            "                \"priority\":5\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"ec86073629ae5f0e7159db64304f5a88\",\n" +
            "                \"name\":\"Cute Baby Panda\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cute.baby.panda\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cute_Baby_Panda.json\",\n" +
            "                \"lock_type\":3,\n" +
            "                \"priority\":4\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"5644b8316a2c3cf9eba469a6d3466aec\",\n" +
            "                \"name\":\"Funny Shiba Inu\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.funny_shiba_inu\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/funny_shiba_inu.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":3\n" +
            "            },\n" +
            "            {\n" +
            "                \"key\":\"39a8eb4d61924b3636de91e2b7f09cd5\",\n" +
            "                \"name\":\"Cool Baby\",\n" +
            "                \"pkg_name\":\"com.ikeyboard.emoji.cool.baby\",\n" +
            "                \"contents_json_url\":\"https://cdn.kikakeyboard.com/json/fun_sticker_maker/Cool_Baby.json\",\n" +
            "                \"lock_type\":5,\n" +
            "                \"priority\":2\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    public final static String stickerMakerHotPageUrl = "https://api-dev.kikakeyboard.com/v1/stickermaker/stickers/list";
    public final static String PNG = "png";
    public final static String GIF = "gif";
    public final static int pngStickerPack = 0;
    public final static int gifStickerPack = 1;
    public static Log log = LogFactory.getLog(StickerLockTypeCheck.class);

    public static Request getSticekrBean(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .addQueryParameter("sign", "12789bb15b5b4a849d3b35c3339fc835")
                .addQueryParameter("pageName", "HOT")
                .addQueryParameter("pageSize", "999")
                .addQueryParameter("obid", "f9de848f4d7a5221cfab2f292a4b1c26f5882731")
                .addQueryParameter("nation", "CN")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl.toString())
                .addHeader("User-Agent", "null/0 (null/null) Country/CN Language/zh System/android Version/23 Screen/320")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>")
                .build();
        return request;
    }


    public static void main(String[] args) throws IOException {
        OldStickerRoot oldStickerRoot = JSONObject.parseObject(OldStickerPageJson, OldStickerRoot.class);
        Response response = new OkHttpClient().newCall(getSticekrBean(stickerMakerHotPageUrl)).execute();
        NewStickerRoot newStickerRoot = JSONObject.parseObject(response.body().string(), NewStickerRoot.class);
        List<Resource_list> oldResource_list = oldStickerRoot.getData().getResource_list();
        List<ResourceList> newResourceList = newStickerRoot.getData().getResourceList();
        Check(oldResource_list, newResourceList);

    }

    public static void Check(List<Resource_list> oldResource_list, List<ResourceList> newResourceList) {
        if (oldResource_list.size() != newResourceList.size()) {
            log.error("stickerPack数量错误");
        }
        for (int i = 0; i < oldResource_list.size(); i++) {
            if (!oldResource_list.get(i).getKey().equals(newResourceList.get(i).getKey())) {
                log.error("第" + (i+1) + "个stickerPack Key不对应");
            }
            if (!oldResource_list.get(i).getName().equals(newResourceList.get(i).getName())) {
                log.error("第" + (i+1) + "个stickerPack Name不对应");
            }
            if (!oldResource_list.get(i).getPkg_name().equals(newResourceList.get(i).getPkgName())) {
                log.error("第" + (i+1) + "个stickerPack PkgName不对应");
            }
            if (oldResource_list.get(i).getPriority() != newResourceList.get(i).getPriority()) {
                log.error("第" + (i+1) + "个stickerPack Priority不对应");
            }
            switch (oldResource_list.get(i).getLock_type()) {
                case 0:
                    if (!newResourceList.get(i).getLockType().equals("free")) {
                        log.error("第" + (i+1) + "个stickerPack LockType不对应");
                    }
                    break;
                case 2:
                    if (!newResourceList.get(i).getLockType().equals("pay")) {
                        log.error("第" + (i+1) + "个stickerPack LockType不对应");
                    }
                    break;
                case 3:
                    if (!newResourceList.get(i).getLockType().equals("pay")) {
                        log.error("第" + (i+1) + "个stickerPack LockType不对应");
                    }
                    break;
                case 4:
                    if (!newResourceList.get(i).getLockType().equals("pay")) {
                        log.error("第" + (i+1) + "个stickerPack LockType不对应");
                    }
                    break;
                case 5:
                    if (!newResourceList.get(i).getLockType().equals("unlock")) {
                        log.error("第" + (i+1) + "个stickerPack LockType不对应");
                    }
                    break;
            }

        }

    }
}