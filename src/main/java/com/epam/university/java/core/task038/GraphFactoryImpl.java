package com.epam.university.java.core.task038;

public class GraphFactoryImpl implements GraphFactory {
    /**
     * Create new Graph instance with designated amount of vertices.
     *
     * @param vertexCount amount of vertices
     * @return new graph instance
     */
    @Override
    public Graph newInstance(int vertexCount) {
        return new GraphImpl(vertexCount);
    }
}
