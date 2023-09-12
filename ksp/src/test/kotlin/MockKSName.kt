import com.google.devtools.ksp.symbol.KSName

data class MockKSName(private val name: String) : KSName {
    override fun asString(): String = name
    override fun getQualifier(): String = name
    override fun getShortName(): String = name
}
