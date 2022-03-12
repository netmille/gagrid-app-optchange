package com.netmillennium.gagrid.app.optchange.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.netmillennium.gagrid.model.Gene;
import com.netmillennium.gagrid.parameter.ChromosomeCriteria;
import com.netmillennium.gagrid.parameter.GAConfiguration;
import com.netmillennium.gagrid.parameter.GAGrid;
import com.netmillennium.gagrid.parameter.GAGridConstants;
import com.netmillennium.gagrid.services.optchange.Coin;
import com.netmillennium.gagrid.services.optchange.OptimizeMakeChangeFitnessFunction;
import com.netmillennium.gagrid.services.optchange.OptimizeMakeChangeTerminateCriteria;

@Configuration
public class OptimizeMakeChangeConfig {

	@Autowired
	private Ignite ignite;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	private GAConfiguration gaConfig;
	
	
	@Bean ("gaConfiguration")
	public GAConfiguration gaConfiguration()
	{
        // Create GAConfiguration
            gaConfig = new GAConfiguration();

            // set Gene Pool
            List<Gene> genes = this.getGenePool();

            // set selection method
            gaConfig.setSelectionMtd(GAGridConstants.SELECTION_METHOD.SELECTION_METHOD_TRUNCATION);

            // set the Chromosome Length to '4' since we have 4 coins.
            gaConfig.setChromosomeLen(4);

            // set population size
            gaConfig.setPopulationSize(500);

            // initialize gene pool
            gaConfig.setGenePool(genes);

            // set Truncate Rate
            gaConfig.setTruncateRate(.10);

            // set Cross Over Rate
            gaConfig.setCrossOverRate(.50);

            // set Mutation Rate
            gaConfig.setMutationRate(.50);

            // create and set Fitness function
            OptimizeMakeChangeFitnessFunction function = new OptimizeMakeChangeFitnessFunction(new Integer(System.getProperty("AMOUNTCHANGE")));
            gaConfig.setFitnessFunction(function);

            // create and set TerminateCriteria
            OptimizeMakeChangeTerminateCriteria termCriteria = new OptimizeMakeChangeTerminateCriteria(ignite);

            ChromosomeCriteria chromosomeCritiera = new ChromosomeCriteria();

            List values = new ArrayList();

            values.add("coinType=QUARTER");
            values.add("coinType=DIME");
            values.add("coinType=NICKEL");
            values.add("coinType=PENNY");

            chromosomeCritiera.setCriteria(values);

            gaConfig.setChromosomeCriteria(chromosomeCritiera);
            gaConfig.setTerminateCriteria(termCriteria);


        return gaConfig;
	}
	
	@Bean("gaGrid")
	@DependsOn("gaConfiguration")
	public GAGrid gagrid()
	{
		GAGrid gaGrid = new GAGrid((GAConfiguration)applicationContext.getBean("gaConfiguration"), ignite);
		
		return gaGrid;
	}
	 /**
     * Helper routine to initialize Gene pool
     * 
     * In typical usecase genes may be stored in database.
     * 
     * @return List<Gene>
     */
    private List<Gene> getGenePool() {
    	
    	List<Gene> list = new ArrayList();
    	
        Gene quarterGene1 = new Gene(new Coin(Coin.CoinType.QUARTER, 3));
        Gene quarterGene2 = new Gene(new Coin(Coin.CoinType.QUARTER, 2));
        Gene quarterGene3 = new Gene(new Coin(Coin.CoinType.QUARTER, 1));
        Gene quarterGene4 = new Gene(new Coin(Coin.CoinType.QUARTER, 0));

        Gene dimeGene1 = new Gene(new Coin(Coin.CoinType.DIME, 2));
        Gene dimeGene2 = new Gene(new Coin(Coin.CoinType.DIME, 1));
        Gene dimeGene3 = new Gene(new Coin(Coin.CoinType.DIME, 0));

        Gene nickelGene1 = new Gene(new Coin(Coin.CoinType.NICKEL, 1));
        Gene nickelGene2 = new Gene(new Coin(Coin.CoinType.NICKEL, 0));

        Gene pennyGene1 = new Gene(new Coin(Coin.CoinType.PENNY, 4));
        Gene pennyGene2 = new Gene(new Coin(Coin.CoinType.PENNY, 3));
        Gene pennyGene3 = new Gene(new Coin(Coin.CoinType.PENNY, 2));
        Gene pennyGene4 = new Gene(new Coin(Coin.CoinType.PENNY, 1));
        Gene pennyGene5 = new Gene(new Coin(Coin.CoinType.PENNY, 0));

        list.add(quarterGene1);
        list.add(quarterGene2);
        list.add(quarterGene3);
        list.add(quarterGene4);
        list.add(dimeGene1);
        list.add(dimeGene2);
        list.add(dimeGene3);
        list.add(nickelGene1);
        list.add(nickelGene2);
        list.add(pennyGene1);
        list.add(pennyGene2);
        list.add(pennyGene3);
        list.add(pennyGene4);
        list.add(pennyGene5);

        return list;
    }
    

}
