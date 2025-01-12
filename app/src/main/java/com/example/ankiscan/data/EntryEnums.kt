enum class Field(val description: String) {
    AGRIC("agriculture"),
    ANAT("anatomy"),
    ARCHEOL("archeology"),
    ARCHIT("architecture"),
    ART("art, aesthetics"),
    ASTRON("astronomy"),
    AUDVID("audiovisual"),
    AVIAT("aviation"),
    BASEB("baseball"),
    BIOCHEM("biochemistry"),
    BIOL("biology"),
    BOT("botany"),
    BUDDH("Buddhism"),
    BUS("business"),
    CARDS("card games"),
    CHEM("chemistry"),
    CHRISTN("Christianity"),
    CLOTH("clothing"),
    COMP("computing"),
    CRYST("crystallography"),
    DENT("dentistry"),
    ECOL("ecology"),
    ECON("economics"),
    ELEC("electricity, elec. eng."),
    ELECTR("electronics"),
    EMBRYO("embryology"),
    ENGR("engineering"),
    ENT("entomology"),
    FILM("film"),
    FINC("finance"),
    FISH("fishing"),
    FOOD("food, cooking"),
    GARDN("gardening, horticulture"),
    GENET("genetics"),
    GEOGR("geography"),
    GEOL("geology"),
    GEOM("geometry"),
    GO("go (game)"),
    GOLF("golf"),
    GRAMM("grammar"),
    GRMYTH("Greek mythology"),
    HANAF("hanafuda"),
    HORSE("horse racing"),
    KABUKI("kabuki"),
    LAW("law"),
    LING("linguistics"),
    LOGIC("logic"),
    MA("martial arts"),
    MAHJ("mahjong"),
    MANGA("manga"),
    MATH("mathematics"),
    MECH("mechanical engineering"),
    MED("medicine"),
    MET("meteorology"),
    MIL("military"),
    MINING("mining"),
    MUSIC("music"),
    NOH("noh"),
    ORNITH("ornithology"),
    PALEO("paleontology"),
    PATHOL("pathology"),
    PHARM("pharmacology"),
    PHIL("philosophy"),
    PHOTO("photography"),
    PHYSICS("physics"),
    PHYSIOL("physiology"),
    POLITICS("politics"),
    PRINT("printing"),
    PSY("psychiatry"),
    PSYANAL("psychoanalysis"),
    PSYCH("psychology"),
    RAIL("railway"),
    ROMMYTH("Roman mythology"),
    SHINTO("Shinto"),
    SHOGI("shogi"),
    SKI("skiing"),
    SPORTS("sports"),
    STAT("statistics"),
    STOCKM("stock market"),
    SUMO("sumo"),
    TELEC("telecommunications"),
    TRADEM("trademark"),
    TV("television"),
    VIDG("video games"),
    ZOOL("zoology");

    companion object {
        fun fromName(name: String): Field? {
            return values().find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

enum class Misc(val description: String) {
    Abbr("abbreviation"),
    Arch("archaic"),
    Char("character"),
    Chn("children's language"),
    Col("colloquial"),
    Company("company name"),
    Creat("creature"),
    Dated("dated term"),
    Dei("deity"),
    Derog("derogatory"),
    Doc("document"),
    Euph("euphemistic"),
    Ev("event"),
    Fam("familiar language"),
    Fem("female term or language"),
    Fict("fiction"),
    Form("formal or literary term"),
    Given("given name or forename, gender not specified"),
    Group("group"),
    Hist("historical term"),
    Hon("honorific or respectful (sonkeigo) language"),
    Hum("humble (kenjougo) language"),
    Id("idiomatic expression"),
    Joc("jocular, humorous term"),
    Leg("legend"),
    MSl("manga slang"),
    Male("male term or language"),
    Myth("mythology"),
    NetSl("Internet slang"),
    Obj("object"),
    Obs("obsolete term"),
    OnMim("onomatopoeic or mimetic word"),
    Organization("organization name"),
    Oth("other"),
    Person("full name of a particular person"),
    Place("place name"),
    Poet("poetical term"),
    Pol("polite (teineigo) language"),
    Product("product name"),
    Proverb("proverb"),
    Quote("quotation"),
    Rare("rare term"),
    Relig("religion"),
    Sens("sensitive"),
    Serv("service"),
    Ship("ship name"),
    Sl("slang"),
    Station("railway station"),
    Surname("family or surname"),
    Uk("word usually written using kana alone"),
    Unclass("unclassified name"),
    Vulg("vulgar expression or word"),
    Work("work of art, literature, music, etc. name"),
    X("rude or X-rated term (not displayed in educational software)"),
    Yoj("yojijukugo");

    companion object {
        fun fromName(name: String): Misc? {
            return values().find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

enum class Dialect(val description: String) {
    BRA("Brazilian"),
    HOB("Hokkaido-ben"),
    KSB("Kansai-ben"),
    KTB("Kantou-ben"),
    KYB("Kyoto-ben"),
    KYU("Kyuushuu-ben"),
    NAB("Nagano-ben"),
    OSB("Osaka-ben"),
    RKB("Ryuukyuu-ben"),
    THB("Touhoku-ben"),
    TSB("Tosa-ben"),
    TSUG("Tsugaru-ben");

    companion object {
        /**
         * Returns a Dialect enum from a case-insensitive name
         *
         * @param name
         * @return
         */
        fun fromName(name: String): Dialect? {
            return entries.find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

enum class SourceLanguageType {
    FULL, PART
}

enum class Gender {
    MALE, FEMALE
}

enum class GlossType {
    LITERAL, FIGURATIVE, EXPLANATION
}

enum class PartOfSpeech(val description: String) {
    AdjF("noun or verb acting prenominally"),
    AdjI("adjective (keiyoushi)"),
    AdjIx("adjective (keiyoushi) - yoi/ii class"),
    AdjKari("'kari' adjective (archaic)"),
    AdjKu("'ku' adjective (archaic)"),
    AdjNa("adjectival nouns or quasi-adjectives (keiyodoshi)"),
    AdjNari("archaic/formal form of na-adjective"),
    AdjNo("nouns which may take the genitive case particle 'no'"),
    AdjPn("pre-noun adjectival (rentaishi)"),
    AdjShiku("'shiku' adjective (archaic)"),
    AdjT("'taru' adjective"),
    Adv("adverb (fukushi)"),
    AdvTo("adverb taking the 'to' particle"),
    Aux("auxiliary"),
    AuxAdj("auxiliary adjective"),
    AuxV("auxiliary verb"),
    Conj("conjunction"),
    Cop("copula"),
    Ctr("counter"),
    Exp("expressions (phrases, clauses, etc.)"),
    Int("interjection (kandoushi)"),
    N("noun (common) (futsuumeishi)"),
    NAdv("adverbial noun (fukushitekimeishi)"),
    NPr("proper noun"),
    NPref("noun, used as a prefix"),
    NSuf("noun, used as a suffix"),
    NT("noun (temporal) (jisoumeishi)"),
    Num("numeric"),
    Pn("pronoun"),
    Pref("prefix"),
    Prt("particle"),
    Suf("suffix"),
    Unc("unclassified"),
    VUnspec("verb unspecified"),
    V1("Ichidan verb"),
    V1S("Ichidan verb - kureru special class"),
    V2aS("Nidan verb with 'u' ending (archaic)"),
    V2bK("Nidan verb (upper class) with 'bu' ending (archaic)"),
    V2bS("Nidan verb (lower class) with 'bu' ending (archaic)"),
    V2dK("Nidan verb (upper class) with 'dzu' ending (archaic)"),
    V2dS("Nidan verb (lower class) with 'dzu' ending (archaic)"),
    V2gK("Nidan verb (upper class) with 'gu' ending (archaic)"),
    V2gS("Nidan verb (lower class) with 'gu' ending (archaic)"),
    V2hK("Nidan verb (upper class) with 'hu/fu' ending (archaic)"),
    V2hS("Nidan verb (lower class) with 'hu/fu' ending (archaic)"),
    V2kK("Nidan verb (upper class) with 'ku' ending (archaic)"),
    V2kS("Nidan verb (lower class) with 'ku' ending (archaic)"),
    V2mK("Nidan verb (upper class) with 'mu' ending (archaic)"),
    V2mS("Nidan verb (lower class) with 'mu' ending (archaic)"),
    V2nS("Nidan verb (lower class) with 'nu' ending (archaic)"),
    V2rK("Nidan verb (upper class) with 'ru' ending (archaic)"),
    V2rS("Nidan verb (lower class) with 'ru' ending (archaic)"),
    V2sS("Nidan verb (lower class) with 'su' ending (archaic)"),
    V2tK("Nidan verb (upper class) with 'tsu' ending (archaic)"),
    V2tS("Nidan verb (lower class) with 'tsu' ending (archaic)"),
    V2wS("Nidan verb (lower class) with 'u' ending and 'we' conjugation (archaic)"),
    V2yK("Nidan verb (upper class) with 'yu' ending (archaic)"),
    V2yS("Nidan verb (lower class) with 'yu' ending (archaic)"),
    V2zS("Nidan verb (lower class) with 'zu' ending (archaic)"),
    V4b("Yodan verb with 'bu' ending (archaic)"),
    V4g("Yodan verb with 'gu' ending (archaic)"),
    V4h("Yodan verb with 'hu/fu' ending (archaic)"),
    V4k("Yodan verb with 'ku' ending (archaic)"),
    V4m("Yodan verb with 'mu' ending (archaic)"),
    V4n("Yodan verb with 'nu' ending (archaic)"),
    V4r("Yodan verb with 'ru' ending (archaic)"),
    V4s("Yodan verb with 'su' ending (archaic)"),
    V4t("Yodan verb with 'tsu' ending (archaic)"),
    V5aru("Godan verb - -aru special class"),
    V5b("Godan verb with 'bu' ending"),
    V5g("Godan verb with 'gu' ending"),
    V5k("Godan verb with 'ku' ending"),
    V5kS("Godan verb - Iku/Yuku special class"),
    V5m("Godan verb with 'mu' ending"),
    V5n("Godan verb with 'nu' ending"),
    V5r("Godan verb with 'ru' ending"),
    V5rI("Godan verb with 'ru' ending (irregular verb)"),
    V5s("Godan verb with 'su' ending"),
    V5t("Godan verb with 'tsu' ending"),
    V5u("Godan verb with 'u' ending"),
    V5uS("Godan verb with 'u' ending (special class)"),
    V5uru("Godan verb - Uru old class verb (old form of Eru)"),
    Vi("intransitive verb"),
    Vk("Kuru verb - special class"),
    Vn("irregular nu verb"),
    Vr("irregular ru verb, plain form ends with -ri"),
    Vs("noun or participle which takes the aux. verb suru"),
    VsC("su verb - precursor to the modern suru"),
    VsI("suru verb - included"),
    VsS("suru verb - special class"),
    Vt("transitive verb"),
    Vz("Ichidan verb - zuru verb (alternative form of -jiru verbs)");

    companion object {
        fun fromName(name: String): PartOfSpeech? {
            return entries.find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

enum class ReadingInfo(val description: String) {
    Gikun("gikun (meaning as reading) or jukujikun (special kanji reading)"),
    Ik("word containing irregular kana usage"),
    Ok("out-dated or obsolete kana usage"),
    Sk("search-only kana form");

    companion object {
        fun fromName(name: String): ReadingInfo? {
            return values().find { it.name.equals(name, ignoreCase = true) }
        }
    }
}

/**
 * Use valueOf() to get the correct case to differ between Ik (kana) and IK (kanji)
 *
 */
enum class KanjiInfo(val description: String) {
    Ateji("ateji (phonetic) reading"),
    Ik("word containing irregular kana usage"),
    IK("word containing irregular kanji usage"),
    Io("irregular okurigana usage"),
    OK("word containing out-dated kanji or kanji usage"),
    RK("rarely-used kanji form"),
    SK("search-only kanji form");
}

/**
 * These are specific to either Kanji or Reading elements
 */
sealed class Priorities(val tag: String, val description: String) {
    data class News1(
        val tag: String = "Mainichi Shimbun 1-12,000",
        val description: String = "Appears in the \"wordfreq\" file compiled by Alexandre Girardi from the Mainichi Shimbun"
    )

    data class News2(
        val tag: String = "Mainichi Shimbun 12,001-24,000",
        val description: String = "Appears in the \"wordfreq\" file compiled by Alexandre Girardi from the Mainichi Shimbun"
    )

    data class Ichi1(
        val tag: String = "Ichimango Goi Bunruishuu High Priority",
        val description: String = "Appears in the \"Ichimango goi bunruishuu\", Senmon Kyouiku Publishing, Tokyo, 1998"
    )

    data class Ichi2(
        val tag: String = "Ichimango Goi Bunruishuu Low Priority",
        val description: String = "Appears in the \"Ichimango goi bunruishuu\", Senmon Kyouiku Publishing, Tokyo, 1998. Observed to have low frequencies in the WWW and in newpapers."
    )

    data class Spec1(
        val tag: String = "High Priority",
        val description: String = "Detected as common, but not included in other lists of common words"
    )

    data class Spec2(
        val tag: String = "Medium Priority",
        val description: String = "Detected as fairly common, but not included in other lists of common words"
    )

    data class Gai1(
        val tag: String = "Mainichi Shimbun 1-12,000 Loanword",
        val description: String = "Appears in the \"wordfreq\" file compiled by Alexandre Girardi from the Mainichi Shimbun"
    )

    data class Gai2(
        val tag: String = "Mainichi Shimbun 12,001-24,000 Loanword",
        val description: String = "Appears in the \"wordfreq\" file compiled by Alexandre Girardi from the Mainichi Shimbun"
    )

    data class Nf(
        val rankingSet: Int,
    ) {
        val description: String =
            "Ranking in wordfreq file compiled by Alexandre Girardi from the Mainichi Shimbun: $rankingSet"
    }
}