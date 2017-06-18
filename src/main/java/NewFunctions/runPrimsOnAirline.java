package NewFunctions;

/**
 * @author Joachim
 */
public class runPrimsOnAirline {

    public void runPrimsOnAirline() {
        /*
        a_visited = []
        a_edges = [i for i in edges if i['airline_code']==airline]
        a_vertices = []
        a_spanning_tree = []
        for e in a_edges:
            if e['destination_code'] not in a_vertices:
                a_vertices.append(e['destination_code'])
            if e['source_code'] not in a_vertices:
                a_vertices.append(e['source_code'])
        # doing the thing
        start = a_vertices[0]
        a_visited.append(start)
        action = True
        itercounter = 0
        while action:
        
            # print(itercounter)
            tmp_path = {'distance': -1}

            for path in a_edges:
                if path['source_code'] in a_visited and path['destination_code'] not in a_visited:
                    itercounter += 1
                    # print(itercounter, ' ', path)
                    if float(path['distance']) > float(tmp_path['distance']):
                        tmp_path = path
                # print('temp path', tmp_path)
                if float(tmp_path['distance'])>-1 and tmp_path not in a_spanning_tree:
                    a_spanning_tree.append(tmp_path)
                    a_visited.append(tmp_path['destination_code'])
                    a_edges.remove(path)
                else:
                    return a_visited, a_spanning_tree


            print('{0} covers {1} routes between {2} airports'.format(airline, len(a_edges), len(a_vertices)))
         */
    }
}
