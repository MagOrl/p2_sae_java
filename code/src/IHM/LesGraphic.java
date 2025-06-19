import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

public class LesGraphic extends VBox {

    private AdministrateurBD adminBD;

    public LesGraphic(String graphChoisi, AdministrateurBD adminBD) throws SQLException, ClassNotFoundException {
        this.adminBD = adminBD;
        Chart c = null;
        switch (graphChoisi) {
            case "Nombre de livres vendus par magasin par année":
                c = nbVendMagAnn();
                break;
            case "CA par thème pour une année":
                c = caParThemePour1Ann();
                break;
            case "Évolution des CA des magasins par mois pour une année":
                c = evolCaMag();
                break;
            case "Comparaison vente en ligne et en magasin":
                c = compVentTypVent();
                break;
            case "Les dix éditeurs les plus importants en nombre d'auteur":
                c = editPlusBig();
                break;
            case "Origine des clients ayant acheté des livres d'un auteur":
                c = originClientAuteur();
                break;
            case "Valeur du stock par magasin":
                c = valStockMag();
                break;
            case "Évolution du chiffre d'affaire total par client":
                c = evoCaParCli();
                break;
            default:
                break;
        }
        this.getChildren().add(c);
    }

    public BarChart<String, Number> nbVendMagAnn() {
        HashMap<String, HashMap<String, Number>> data = null;
        try {
            data = this.adminBD.requeteNbVendMagAnne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("mois");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("CA");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Evolution CA des magasins par mois en 2024");
        for (String annee : data.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(annee);
            for (String mag : data.get(annee).keySet()) {
                series.getData().add(new XYChart.Data(mag, data.get(annee).get(mag)));
            }
            barChart.getData().add(series);
        }
        return barChart;
    }

    public PieChart caParThemePour1Ann() {
        ArrayList<Map.Entry<String, Integer>> data = null;
        try {
            data = this.adminBD.requeteCAThemeAnnee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Chiffre d'affaire 2025 par thème");
        for (Map.Entry<String, Integer> entree : data) {
            pieChart.getData().add(new PieChart.Data(entree.getKey(), entree.getValue()));
        }
        return pieChart;
    }

    public StackedAreaChart evolCaMag() {
        HashMap<String, HashMap<String, Number>> data = null;
        try {
            data = this.adminBD.requeteEvoCAMag();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("mois");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("CA");
        StackedAreaChart stackedAreaChart = new StackedAreaChart(xAxis, yAxis);
        stackedAreaChart.setTitle("Evolution CA des magasin par mois en 2024");
        for (String mag : data.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(mag);
            for (String mois : data.get(mag).keySet()) {
                series.getData().add(new XYChart.Data(mois, data.get(mag).get(mois)));
            }
            stackedAreaChart.getData().add(series);
        }
        return stackedAreaChart;
    }

    public LineChart compVentTypVent() {
        HashMap<String, HashMap<String, Number>> data = null;
        try {
            data = this.adminBD.requeteCompVenteLMAnnee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("années");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("CA");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Comparaison vente en ligne et en magasin");
        for (String mag : data.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(mag);
            for (String mois : data.get(mag).keySet()) {
                series.getData().add(new XYChart.Data(mois, data.get(mag).get(mois)));
            }
            lineChart.getData().add(series);
        }
        return lineChart;
    }

    public BarChart editPlusBig() {
        ArrayList<Map.Entry<String, Integer>> data = null;
        try {
            data = this.adminBD.requeteDixeditPlusImportants();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("nb auteurs");
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("éditeurs");
        xAxis.setTickLabelRotation(90);
        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("dix éditeurs les plus important");
        for (Map.Entry<String, Integer> val : data) {
            XYChart.Series series = new XYChart.Series();
            series.getData().add(new XYChart.Data(val.getValue(), val.getKey()));
            barChart.getData().add(series);
        }
        return barChart;
    }

    public PieChart originClientAuteur() {
        ArrayList<Map.Entry<String, Integer>> data = null;
        try {
            data = this.adminBD.requeteOrigineClientAuteur();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Origine des clients ayant des livres René Goscinny");
        for (Map.Entry<String, Integer> entree : data) {
            pieChart.getData().add(new PieChart.Data(entree.getKey(), entree.getValue()));
        }
        return pieChart;
    }

    public PieChart valStockMag() {
        ArrayList<Map.Entry<String, Integer>> data = null;
        try {
            data = this.adminBD.requeteValeurStockMag();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Valeur du stock par magasin");
        for (Map.Entry<String, Integer> entree : data) {
            pieChart.getData().add(new PieChart.Data(entree.getKey(), entree.getValue()));
        }
        return pieChart;
    }

    public LineChart evoCaParCli() {
        HashMap<String, HashMap<String, Number>> data = null;
        try {
            data = this.adminBD.requeteEvoCAClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("années");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("CA");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Evolution CA Total par client");
        for (String mag : data.keySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(mag);
            List<String> l = new ArrayList<>(data.get(mag).keySet());
            Collections.sort(l);
            for (String mois : l) {
                series.getData().add(new XYChart.Data(mois, data.get(mag).get(mois)));
            }
            lineChart.getData().add(series);
        }
        return lineChart;
    }
}
