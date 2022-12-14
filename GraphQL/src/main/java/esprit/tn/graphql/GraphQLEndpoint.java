package esprit.tn.graphql;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;

import esprit.tn.repository.StudentRepository;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

@WebServlet(urlPatterns = "/graphql")

public class GraphQLEndpoint extends SimpleGraphQLServlet {

	public GraphQLEndpoint() {
		super (buildSchema());
	}
	private static GraphQLSchema buildSchema() {
	StudentRepository studentRepository = new StudentRepository();
	return SchemaParser .newParser()
			.file("schema.graphqls")
			.resolvers(new Query(studentRepository))
			.build()
			.makeExecutableSchema();
	}
}
