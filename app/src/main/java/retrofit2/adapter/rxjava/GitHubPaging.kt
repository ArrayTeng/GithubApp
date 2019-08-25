package retrofit2.adapter.rxjava

/**
 * @author tengfei
 * date 2019-08-25 13:50
 * email tengfeigo@outlook.com
 * description
 */
class GitHubPaging<T> : ArrayList<T>() {

    companion object {
        const val URL_PATTERN = """(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""
    }

    private val relMap = HashMap<String, String?>().withDefault { null }

    private val first by relMap
    private val last by relMap
    private val next by relMap
    private val prv by relMap

    val isLast
        get() = last == null

    val hasNext
        get() = next!=null

    val hasPrv
        get() = prv!=null

    val isFirst
        get() = first == null

    operator fun get(key: String): String? {
        return relMap[key]
    }

    fun setupLinks(link: String){
        Regex("""<($URL_PATTERN)>; rel="(\w+)"""").findAll(link).asIterable()
                .map {
                    matchResult ->
                    val url = matchResult.groupValues[1]
                    relMap[matchResult.groupValues[3]] = url // next=....
                }
    }

    fun mergeData(paging: GitHubPaging<T>): GitHubPaging<T>{
        addAll(paging)
        relMap.clear()
        relMap.putAll(paging.relMap)
        return this
    }


}