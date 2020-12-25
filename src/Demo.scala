import org.neo4j.driver.{AuthTokens, GraphDatabase}

object Demo {
  def main(args: Array[String]): Unit = {
    // cluster: 127.0.0.1:7610, 127.0.0.1:7620, 127.0.0.1:7630
    val driver = GraphDatabase.driver("bolt://xuchl-jump.all123.net:7610", AuthTokens.basic("neo4j", "neo4j"))

    //write or read by session
    val session = driver.session()
    session.run("create (n:test{name:'test'})")
    val res = session.run("match (n) return n")
    res.list()
    session.close()

    // write or read by transaction
    val session2 = driver.session()
    val tx = session2.beginTransaction()
    tx.run("create (n:test2{name:'test2'})")
    val res2 = tx.run("match (n) return n")
    tx.success()
    tx.close()
    session2.close()
    driver.close()
  }
}
